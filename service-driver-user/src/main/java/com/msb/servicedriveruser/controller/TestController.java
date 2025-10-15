package com.msb.servicedriveruser.controller;

import com.msb.dao.ResponseResult;
import com.msb.servicedriveruser.service.DriverUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private DriverUserService driverUserService;
    @RequestMapping("/test")
    public String test(){
        return "service-driver-user";
    }
    @RequestMapping("/test2")
    public ResponseResult test2(){
        return driverUserService.test();
    }
}
