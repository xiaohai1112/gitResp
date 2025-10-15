package com.msb.apiboos.service;

import com.msb.apiboos.romate.ServiceDriverUserClient;
import com.msb.dao.DriverUser;
import com.msb.dao.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoosService {
    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;
    public ResponseResult addDriver(DriverUser driverUser){
        return ResponseResult.success(serviceDriverUserClient.addDriver(driverUser));
    }
    public ResponseResult updateDriver(DriverUser driverUser){
        return ResponseResult.success(serviceDriverUserClient.updateDriver(driverUser));
    }
}
