package com.msb.servicedriveruser.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.msb.dao.Car;
import com.msb.dao.ResponseResult;
import com.msb.servicedriveruser.mapper.CarMapper;
import com.msb.servicedriveruser.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author child
 * @since 2025-10-16
 */
@Service
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements ICarService {
    @Autowired
    private CarMapper carMapper;

    @Override
    public ResponseResult addCar(Car car) {
        LocalDateTime now=LocalDateTime.now();
        car.setGmtCreate(now);
        car.setGmtModified(now);
        carMapper.insert(car);
        return ResponseResult.success("");
    }
}
