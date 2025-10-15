package com.msb.apidriver.controller;

import com.msb.apidriver.service.DriverService;
import com.msb.dao.DriverUser;
import com.msb.dao.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DriverController {
    @Autowired
    private DriverService driverService;
    @PutMapping("/user")
    public ResponseResult update(@RequestBody DriverUser driverUser){
        return driverService.updateUser(driverUser);
    }
}
