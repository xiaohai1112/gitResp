package com.msb.servicedriveruser.service;

import com.msb.dao.DriverUser;
import com.msb.dao.ResponseResult;
import com.msb.servicedriveruser.mapper.DriverUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;


@Service
public class DriverUserService {
    @Autowired
    private DriverUserMapper driverUserMapper;
    public ResponseResult test(){
        return ResponseResult.success(driverUserMapper.selectById(1));
    }
    public ResponseResult addDriverUser(DriverUser driverUser){
        LocalDateTime now = LocalDateTime.now();
        driverUser.setGmtCreate(now);
        driverUser.setGmtModified(now);
        return ResponseResult.success(driverUserMapper.insert(driverUser));
    }
}
