package com.msb.servicedriveruser.service;

import com.msb.dao.ResponseResult;
import com.msb.servicedriveruser.mapper.DriverUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class DriverUserService {
    @Autowired
    private DriverUserMapper driverUserMapper;
    public ResponseResult test(){
        return ResponseResult.success(driverUserMapper.selectById(1));
    }
}
