package com.msb.apidriver.service;

import com.msb.apidriver.remote.ServiceDriverClient;
import com.msb.dao.DriverUser;
import com.msb.dao.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DriverService {
    @Autowired
    private ServiceDriverClient serviceDriverClient;
    public ResponseResult updateUser(DriverUser driverUser){
        serviceDriverClient.updateDriver(driverUser);
        return ResponseResult.success("");
    }
}
