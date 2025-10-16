package com.msb.apiboos.controller;

import com.msb.apiboos.service.BoosService;
import com.msb.dao.Car;
import com.msb.dao.DriverCarBindingRelationship;
import com.msb.dao.DriverUser;
import com.msb.dao.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoosController {
    @Autowired
    private BoosService boosService;
    @PostMapping("/driver-user")
    public ResponseResult addDriver(@RequestBody DriverUser driverUser){
        return boosService.addDriver(driverUser);
    }
    @PutMapping("/driver-user")
    public ResponseResult updateDriver(@RequestBody DriverUser driverUser){
        return boosService.updateDriver(driverUser);
    }
    @PostMapping("/car")
    public ResponseResult addCar(@RequestBody Car car){
        return boosService.addCar(car);
    }
    @PostMapping("/driver_car_binding_relationship/bind")
    public ResponseResult addDriverCarBindingRelationship(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship){
        return boosService.addDriverCarBindingRelationship(driverCarBindingRelationship);
    }
    @PostMapping("/driver_car_binding_relationship/unbind")
    public ResponseResult updateDriverCarBindingRelationship(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship){
        return boosService.updateDriverCarBindingRelationship(driverCarBindingRelationship);
    }
}
