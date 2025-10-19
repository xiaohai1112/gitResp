package com.msb.servicedriveruser.controller;


import com.msb.dao.Car;
import com.msb.dao.ResponseResult;
import com.msb.servicedriveruser.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private ICarService iCarService;
    @PostMapping("/car")
    public ResponseResult add(@RequestBody Car car){
        return iCarService.addCar(car);
    }
    @GetMapping("/car")
    public ResponseResult<Car> getCarById(Long carId){
        return iCarService.getCarById(carId);
    }
}
