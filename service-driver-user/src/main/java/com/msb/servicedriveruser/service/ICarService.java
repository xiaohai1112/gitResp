package com.msb.servicedriveruser.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.msb.dao.Car;
import com.msb.dao.ResponseResult;
import com.msb.servicedriveruser.mapper.CarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author child
 * @since 2025-10-16
 */
@Service
public class ICarService {

    @Autowired
    private CarMapper carMapper;
    public ResponseResult addCar(Car car) {
        LocalDateTime now=LocalDateTime.now();
        car.setGmtCreate(now);
        car.setGmtModified(now);
        carMapper.insert(car);
        return ResponseResult.success("");
    }

}
