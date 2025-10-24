package com.msb.servicedriveruser.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.msb.constant.CommonStatusEnum;
import com.msb.constant.DriverCarConstant;
import com.msb.dao.*;
import com.msb.responese.OrderResponse;
import com.msb.servicedriveruser.mapper.CarMapper;
import com.msb.servicedriveruser.mapper.DriverCarBindingRelationshipMapper;
import com.msb.servicedriveruser.mapper.DriverUserMapper;
import com.msb.servicedriveruser.mapper.DriverUserWorkStatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class DriverUserService {
    @Autowired
    private DriverUserMapper driverUserMapper;
    @Autowired
    private DriverUserWorkStatusMapper driverUserWorkStatusMapper;
    @Autowired
    private CarMapper carMapper;

    public ResponseResult test(){
        return ResponseResult.success(driverUserMapper.selectById(1));
    }
    public ResponseResult addDriverUser(DriverUser driverUser){
        LocalDateTime now = LocalDateTime.now();
        driverUser.setGmtCreate(now);
        driverUser.setGmtModified(now);
        driverUserMapper.insert(driverUser);
        //初始化 司机状态表
        DriverUserWorkStatus driverUserWorkStatus = new DriverUserWorkStatus();
        driverUserWorkStatus.setDriverId(driverUser.getId());
        driverUserWorkStatus.setWorkStatus(DriverCarConstant.DRIVER_WORK_STATUS_STOP);
        driverUserWorkStatus.setGmtCreate(now);
        driverUserWorkStatus.setGmtModified(now);
        driverUserWorkStatusMapper.insert(driverUserWorkStatus);
        return ResponseResult.success("");
    }
    public ResponseResult updateDriverUser(DriverUser driverUser){
        LocalDateTime now=LocalDateTime.now();
        driverUser.setGmtModified(now);
        driverUserMapper.updateById(driverUser);
        return ResponseResult.success();
    }
    public ResponseResult<DriverUser> findDriverUserByPhone(String driverPhone){
        Map<String,Object> map=new HashMap<>();
        map.put("driver_phone",driverPhone);
        map.put("state", DriverCarConstant.DRIVER_STATE_VALID);
        List<DriverUser> driverUsers = driverUserMapper.selectByMap(map);
        if (driverUsers.isEmpty()){
            return ResponseResult.fail(CommonStatusEnum.DRIVER_NOT_EXIST.getCode(),CommonStatusEnum.DRIVER_NOT_EXIST.getValue());
        }
        DriverUser driverUser = driverUsers.get(0);
        return ResponseResult.success(driverUser);
    }
    @Autowired
    DriverCarBindingRelationshipMapper driverCarBindingRelationshipMapper;
    public ResponseResult<OrderResponse> getAvailableDriver(Long carId){
        QueryWrapper<DriverCarBindingRelationship> queryWrapper = new QueryWrapper<>();
        //绑定状态
        queryWrapper.eq("car_id",carId);
        queryWrapper.eq("bind_state",DriverCarConstant.DRIVER_CAR_BIND);
        DriverCarBindingRelationship driverCarBindingRelationship = driverCarBindingRelationshipMapper.selectOne(queryWrapper);
        Long driverId = driverCarBindingRelationship.getDriverId();
        QueryWrapper<DriverUserWorkStatus> driverUserWorkStatusQueryWrapper = new QueryWrapper<>();
        //查看司机工作状态
        driverUserWorkStatusQueryWrapper.eq("driver_id",driverId);
        driverUserWorkStatusQueryWrapper.eq("work_status",DriverCarConstant.DRIVER_WORK_STATUS_START);
        DriverUserWorkStatus driverUserWorkStatus = driverUserWorkStatusMapper.selectOne(driverUserWorkStatusQueryWrapper);
        if (null==driverUserWorkStatus){
            return ResponseResult.fail(CommonStatusEnum.AVAILABLE_DRIVER_NOT_EXIST.getCode(),CommonStatusEnum.AVAILABLE_DRIVER_NOT_EXIST.getValue());
        }else {
            QueryWrapper<DriverUser> driverUserQueryWrapper = new QueryWrapper<>();
            //查司机信息
            driverUserQueryWrapper.eq("id",driverId);
            DriverUser driverUser = driverUserMapper.selectOne(driverUserQueryWrapper);

            QueryWrapper<Car> carQueryWrapper = new QueryWrapper<>();
            carQueryWrapper.eq("id",carId);
            Car car = carMapper.selectOne(carQueryWrapper);

            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setCarId(carId);
            orderResponse.setDriverId(driverId);
            orderResponse.setDriverPhone(driverUser.getDriverPhone());

            orderResponse.setLicenseId(driverUser.getLicenseId());
            orderResponse.setVehicleNo(car.getVehicleNo());

            return ResponseResult.success(orderResponse);
        }
    }
}
