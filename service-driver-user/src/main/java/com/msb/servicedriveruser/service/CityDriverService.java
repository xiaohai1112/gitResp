package com.msb.servicedriveruser.service;

import com.msb.dao.ResponseResult;
import com.msb.servicedriveruser.mapper.DriverUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityDriverService {
    @Autowired
    DriverUserMapper driverUserMapper;
    public ResponseResult<Boolean> selectDriverByCityCodeCount(String cityCode){
        int i = driverUserMapper.selectDriverByCityCodeCount(cityCode);
        if (i>0){
            return ResponseResult.success(true);
        }else
            return ResponseResult.success(false);
    }
}
