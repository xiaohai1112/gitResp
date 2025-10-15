package com.msb.apiboos.controller;

import com.msb.apiboos.service.BoosService;
import com.msb.dao.DriverUser;
import com.msb.dao.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoosController {
    @Autowired
    private BoosService boosService;
    @PostMapping("/driver-user")
    public ResponseResult add(@RequestBody DriverUser driverUser){
        return boosService.addDriver(driverUser);
    }
}
