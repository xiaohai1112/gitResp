package com.msb.servicedriveruser.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.msb.constant.CommonStatusEnum;
import com.msb.constant.DriverCarConstant;
import com.msb.dao.DriverCarBindingRelationship;
import com.msb.dao.ResponseResult;
import com.msb.servicedriveruser.mapper.DriverCarBindingRelationshipMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author child
 * @since 2025-10-16
 */
@Service
public class DriverCarBindingRelationshipService {
    @Autowired
    private DriverCarBindingRelationshipMapper driverCarBindingRelationshipMapper;
    public ResponseResult addDriverCarBindingRelationship(DriverCarBindingRelationship driverCarBindingRelationship){
        QueryWrapper<DriverCarBindingRelationship> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("driver_id",driverCarBindingRelationship.getDriverId());
        queryWrapper.eq("car_id",driverCarBindingRelationship.getCarId());
        queryWrapper.eq("bind_state",DriverCarConstant.DRIVER_CAR_BIND);
        Integer i = driverCarBindingRelationshipMapper.selectCount(queryWrapper);
        if (i.intValue()>0){
            return ResponseResult.fail(CommonStatusEnum.DRIVER_CAR_BIND_EXIST.getCode(),CommonStatusEnum.DRIVER_CAR_BIND_EXIST.getValue());
        }
        //司机已被绑定
        queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("driver_id",driverCarBindingRelationship.getDriverId());
        queryWrapper.eq("bind_state",DriverCarConstant.DRIVER_CAR_BIND);
        i = driverCarBindingRelationshipMapper.selectCount(queryWrapper);
        if (i.intValue()>0){
            return ResponseResult.fail(CommonStatusEnum.DRIVER_BIND_EXIST.getCode(),CommonStatusEnum.DRIVER_BIND_EXIST.getValue());
        }
        //车辆已被绑定
        queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("car_id",driverCarBindingRelationship.getCarId());
        queryWrapper.eq("bind_state",DriverCarConstant.DRIVER_CAR_BIND);
        i = driverCarBindingRelationshipMapper.selectCount(queryWrapper);
        if (i.intValue()>0){
            return ResponseResult.fail(CommonStatusEnum.CAR_BIND_EXIST.getCode(),CommonStatusEnum.CAR_BIND_EXIST.getValue());
        }
        LocalDateTime now=LocalDateTime.now();
        driverCarBindingRelationship.setBindingTime(now);
        driverCarBindingRelationship.setBindState(DriverCarConstant.DRIVER_CAR_BIND);
        driverCarBindingRelationshipMapper.insert(driverCarBindingRelationship);
        return ResponseResult.success("");
    }
    public ResponseResult updateDriverCarBindingRelationship(DriverCarBindingRelationship driverCarBindingRelationship){
        Map<String,Object> map=new HashMap<>();
        map.put("driver_id",driverCarBindingRelationship.getDriverId());
        map.put("car_id",driverCarBindingRelationship.getCarId());
        map.put("bind_state",DriverCarConstant.DRIVER_CAR_BIND);
        List<DriverCarBindingRelationship> driverCarBindingRelationships = driverCarBindingRelationshipMapper.selectByMap(map);
        if (driverCarBindingRelationships.isEmpty()){
            return ResponseResult.fail(CommonStatusEnum.DRIVER_CAR_BIND_NOT_EXIST.getCode(),CommonStatusEnum.DRIVER_CAR_BIND_NOT_EXIST.getValue());
        }
        DriverCarBindingRelationship dcr=driverCarBindingRelationships.get(0);
        LocalDateTime now =LocalDateTime.now();
        dcr.setUnBindingTime(now);
        dcr.setBindState(DriverCarConstant.DRIVER_CAR_UNBIND);
        driverCarBindingRelationshipMapper.updateById(dcr);
        return ResponseResult.success("");
    }

}
