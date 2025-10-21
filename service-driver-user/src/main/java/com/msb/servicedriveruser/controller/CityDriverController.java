package com.msb.servicedriveruser.controller;

import com.msb.dao.ResponseResult;
import com.msb.servicedriveruser.service.CityDriverService;
import com.msb.servicedriveruser.service.DriverUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/city-driver")
public class CityDriverController {
    @Autowired
    CityDriverService cityDriverService;
    @GetMapping("/count")
    public ResponseResult<Boolean> find(String cityCode){
        return cityDriverService.selectDriverByCityCodeCount(cityCode);
    }
}
