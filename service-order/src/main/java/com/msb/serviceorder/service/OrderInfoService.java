package com.msb.serviceorder.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.msb.Utils.RedisPrefixUtils;
import com.msb.constant.CommonStatusEnum;
import com.msb.constant.OrderConstant;
import com.msb.dao.OrderInfo;
import com.msb.dao.PriceRule;
import com.msb.dao.ResponseResult;
import com.msb.request.OrderRequest;
import com.msb.serviceorder.mapper.OrderInfoMapper;
import com.msb.serviceorder.romate.ServiceDriverUserClient;
import com.msb.serviceorder.romate.ServicePriceClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务类
 * </p>
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
    public ResponseResult add(OrderRequest orderRequest){
        //判断该地是否有可用司机
        String cityCode=orderRequest.getAddress();
        ResponseResult<Boolean> booleanResponseResult = serviceDriverUserClient.find(cityCode);
        System.out.println(booleanResponseResult);
        if (!booleanResponseResult.getData()){
            return ResponseResult.fail(CommonStatusEnum.DRIVER_NOT_CITY.getCode(),CommonStatusEnum.DRIVER_NOT_CITY.getValue());
        }

        //判断计价规则的版本是否是最新的版本
//        ResponseResult<Boolean> latestVersion = servicePriceClient.isLatestVersion(orderRequest.getFareType(), orderRequest.getFareVersion());
//        if (!latestVersion.getData()){
//            return ResponseResult.fail(CommonStatusEnum.PICE_RULE_NOT_NEW.getCode(),CommonStatusEnum.PICE_RULE_NOT_NEW.getValue());
//        }
        //判断该城市是否支持乘车
        if (!isPriceRuleExists(orderRequest)){
            return ResponseResult.fail(CommonStatusEnum.CITY_NOT_SERVICE.getCode(),CommonStatusEnum.CITY_NOT_SERVICE.getValue());
        }
        //判断下单手机号是否是黑名单中的
        if (isBlackDevice(orderRequest))
            return ResponseResult.fail(CommonStatusEnum.LIMIT_ORDERS.getCode(), CommonStatusEnum.LIMIT_ORDERS.getValue());
        //判断正在进行的订单是否还能下单
        int orderNum = getOrderNum(orderRequest.getPassengerId());
        if (orderNum>0){
            return ResponseResult.fail(CommonStatusEnum.ORDER_EXIST.getCode(),CommonStatusEnum.ORDER_EXIST.getValue());
        }
        //创建订单
//        OrderInfo orderInfo = new OrderInfo();
//        BeanUtils.copyProperties(orderRequest,orderInfo);
//        orderInfo.setOrderStatus(OrderConstant.ORDER_START);
//        LocalDateTime now=LocalDateTime.now();
//        orderInfo.setGmtCreate(now);
//        orderInfo.setGmtModified(now);
//        orderInfoMapper.insert(orderInfo);
        return ResponseResult.success();
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

    public int getOrderNum(Long passengerId){
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
}
