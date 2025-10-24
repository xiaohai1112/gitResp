package com.msb.serviceorder.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.msb.Utils.RedisPrefixUtils;
import com.msb.constant.CommonStatusEnum;
import com.msb.constant.OrderConstant;
import com.msb.dao.OrderInfo;
import com.msb.dao.PriceRule;
import com.msb.dao.ResponseResult;
import com.msb.request.OrderRequest;
import com.msb.request.PriceRuleNewRequest;
import com.msb.responese.OrderResponse;
import com.msb.responese.TerminalResponse;
import com.msb.serviceorder.mapper.OrderInfoMapper;
import com.msb.serviceorder.romate.ServiceDriverUserClient;
import com.msb.serviceorder.romate.ServiceMapClient;
import com.msb.serviceorder.romate.ServicePriceClient;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author child
 * @since 2025-10-20
 */
@Service
public class OrderInfoService {
    @Autowired
    ServiceDriverUserClient serviceDriverUserClient;
    @Autowired
    private ServicePriceClient servicePriceClient;
    @Autowired
    OrderInfoMapper orderInfoMapper;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    ServiceMapClient serviceMapClient;
    public ResponseResult add(OrderRequest orderRequest){
        PriceRuleNewRequest priceRuleNewRequest = new PriceRuleNewRequest();
        priceRuleNewRequest.setFareType(orderRequest.getFareType());
        priceRuleNewRequest.setFareVersion(orderRequest.getFareVersion());
        //判断计价规则的版本是否是最新的版本
        ResponseResult<Boolean> latestVersion = servicePriceClient.isLatestVersion(priceRuleNewRequest);
        if (!latestVersion.getData()){
            return ResponseResult.fail(CommonStatusEnum.PICE_RULE_NOT_NEW.getCode(),CommonStatusEnum.PICE_RULE_NOT_NEW.getValue());
        }
        //判断该城市是否支持乘车
        if (!isPriceRuleExists(orderRequest)){
            return ResponseResult.fail(CommonStatusEnum.CITY_NOT_SERVICE.getCode(),CommonStatusEnum.CITY_NOT_SERVICE.getValue());
        }
        //判断该地是否有可用司机
        String cityCode=orderRequest.getAddress();
        ResponseResult<Boolean> booleanResponseResult = serviceDriverUserClient.find(cityCode);
        System.out.println(booleanResponseResult);
        if (!booleanResponseResult.getData()){
            return ResponseResult.fail(CommonStatusEnum.DRIVER_NOT_CITY.getCode(),CommonStatusEnum.DRIVER_NOT_CITY.getValue());
        }
        //判断下单手机号是否是黑名单中的
        if (isBlackDevice(orderRequest))
            return ResponseResult.fail(CommonStatusEnum.LIMIT_ORDERS.getCode(), CommonStatusEnum.LIMIT_ORDERS.getValue());

        //判断正在进行的订单是否还能下单
        int orderNum = getPassengerOrderNum(orderRequest.getPassengerId());
        if (orderNum>0){
            return ResponseResult.fail(CommonStatusEnum.ORDER_EXIST.getCode(),CommonStatusEnum.ORDER_EXIST.getValue());
        }

        //创建订单
        OrderInfo orderInfo = new OrderInfo();
        BeanUtils.copyProperties(orderRequest,orderInfo);
        orderInfo.setOrderStatus(OrderConstant.ORDER_START);
        LocalDateTime now=LocalDateTime.now();
        orderInfo.setGmtCreate(now);
        orderInfo.setGmtModified(now);
        orderInfoMapper.insert(orderInfo);
        //搜索车辆
        aroundsearch(orderInfo);
        return ResponseResult.success();
    }

    public synchronized void aroundsearch(OrderInfo orderInfo) {
        String depLatitude = orderInfo.getDepLatitude();
        String depLongitude = orderInfo.getDepLongitude();
        String center = depLatitude+","+depLongitude;
        int radius = 2000;
        List<Integer> radiusList=new ArrayList<>();
        radiusList.add(2000);
        radiusList.add(4000);
        radiusList.add(5000);
        ResponseResult<List<TerminalResponse>> listResponseResult =null;
        raduis:
        for (int r:radiusList) {
            radius=r;
            listResponseResult = serviceMapClient.terminalAroundsearch(center, radius);

            System.out.println(("在" + radius + "米范围内寻找车辆"+ JSONArray.fromObject(listResponseResult.getData()).toString()));
            //[{"carId":1980825251740209154,"tid":"1557275988"}]
            //获取终端
            List<TerminalResponse> data = listResponseResult.getData();
            for (int i = 0; i < data.size(); i++) {
                TerminalResponse terminalResponse = data.get(i);
                Long carId = terminalResponse.getCarId();
                Long latitude = terminalResponse.getLatitude();
                Long longitude = terminalResponse.getLongitude();
                ResponseResult<OrderResponse> availableDriver = serviceDriverUserClient.getAvailableDriver(carId);
                if (availableDriver.getCode()==CommonStatusEnum.AVAILABLE_DRIVER_NOT_EXIST.getCode()){
                    System.out.println(("没有司机" + carId));
                    continue raduis;
                }else {
                    System.out.println(("找到车了" + carId));
                    OrderResponse orderResponse = availableDriver.getData();
                    Long driverId = orderResponse.getDriverId();
                    //判断正在进行的订单是否还能下单
                    if (getDriverOrderNum(driverId)>0){
                        continue ;
                    }
                    //订单匹配司机

                    //司机
                    orderInfo.setDriverId(driverId);
                    orderInfo.setDriverPhone(orderResponse.getDriverPhone());
                    orderInfo.setCarId(carId);
                    //地图
                    orderInfo.setReceiveOrderCarLongitude(longitude+"");
                    orderInfo.setReceiveOrderCarLatitude(latitude+"");
                    orderInfo.setReceiveOrderTime(LocalDateTime.now());

                    orderInfo.setLicenseId(orderResponse.getLicenseId());
                    orderInfo.setVehicleNo(orderResponse.getVehicleNo());
                    orderInfo.setOrderStatus(OrderConstant.DRIVER_RECEIVE_ORDER);
                    orderInfoMapper.updateById(orderInfo);
                    break raduis;
                }

            }
            //解析终端

            //查询车辆信息
            //找到符合车辆进行派单
            //派单成功，退出循环
        }
    }

    private boolean isPriceRuleExists(OrderRequest orderRequest){
        String fareType = orderRequest.getFareType();
        int indexOf = fareType.indexOf("$");
        String cityCode = fareType.substring(0, indexOf);
        String vehicleType = fareType.substring(indexOf + 1);
        PriceRule priceRule = new PriceRule();
        priceRule.setCityCode(cityCode);
        priceRule.setVehicleType(vehicleType);
        ResponseResult<Boolean> exits = servicePriceClient.isExits(priceRule);
        return exits.getData();
    }

    private boolean isBlackDevice(OrderRequest orderRequest) {
        String deviceCode = orderRequest.getDeviceCode();
        //生成key
        String deviceCodeKey = RedisPrefixUtils.deviceCodePrefix + deviceCode;
        Boolean b = stringRedisTemplate.hasKey(deviceCodeKey);
        if (b) {
            String s = stringRedisTemplate.opsForValue().get(deviceCodeKey);
            int i = Integer.parseInt(s);
            if (i >= 2) {
                //下单次数达标，不允许优惠价下单
                return true;
            } else {
                stringRedisTemplate.opsForValue().increment(deviceCodeKey);
            }
        } else {
            stringRedisTemplate.opsForValue().setIfAbsent(deviceCodeKey, "1", 1L, TimeUnit.HOURS);
        }
        return false;
    }

    /**
     * 查看乘客有没有正在进行的订单
     * @param passengerId
     * @return
     */
    public int getPassengerOrderNum(Long passengerId){
        QueryWrapper<OrderInfo> orderInfoQueryWrapper = new QueryWrapper<>();
        orderInfoQueryWrapper.eq("passenger_id",passengerId);
        orderInfoQueryWrapper.and(wrapper->wrapper.eq("order_status",OrderConstant.ORDER_START)
                .or().eq("order_status",OrderConstant.DRIVER_RECEIVE_ORDER)
                .or().eq("order_status",OrderConstant.DRIVER_TOO_PICK_UP_PASSENGER)
                .or().eq("order_status",OrderConstant.DRIVER_ARRIVED_DEPARTURE)
                .or().eq("order_status",OrderConstant.PICK_UP_PASSENGER)
                .or().eq("order_status",OrderConstant.PASSENGER_GETOFF)
                .or().eq("order_status",OrderConstant.TO_START_PAY));
        Integer i = orderInfoMapper.selectCount(orderInfoQueryWrapper);
        return i;
    }
    /**
     * 查看司机有没有正在进行的订单
     * @param driverId
     * @return
     */
    public int getDriverOrderNum(Long driverId){
        QueryWrapper<OrderInfo> orderInfoQueryWrapper = new QueryWrapper<>();
        orderInfoQueryWrapper.eq("driver_id",driverId);
        orderInfoQueryWrapper.and(wrapper->wrapper.eq("order_status",OrderConstant.DRIVER_RECEIVE_ORDER)
                .or().eq("order_status",OrderConstant.DRIVER_TOO_PICK_UP_PASSENGER)
                .or().eq("order_status",OrderConstant.DRIVER_ARRIVED_DEPARTURE)
                .or().eq("order_status",OrderConstant.PICK_UP_PASSENGER));
        Integer i = orderInfoMapper.selectCount(orderInfoQueryWrapper);
        System.out.println("司机id："+driverId+"正在进行的订单:"+i);
        return i;
    }
}
