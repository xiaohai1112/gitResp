package com.msb.servicedriveruser.controller;


import com.msb.dao.Car;
import com.msb.dao.ResponseResult;
import com.msb.servicedriveruser.service.impl.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author child
 * @since 2025-10-16
 */
@RestController
public class CarController {
    @Autowired
    private CarServiceImpl carServiceimpl;
    @PostMapping("/car")
    public ResponseResult add(@RequestBody Car car){
        return carServiceimpl.addCar(car);
    }

}
