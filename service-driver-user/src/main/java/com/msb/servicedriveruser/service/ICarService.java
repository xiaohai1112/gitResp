package com.msb.servicedriveruser.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.msb.dao.Car;
import com.msb.dao.ResponseResult;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author child
 * @since 2025-10-16
 */
public interface ICarService extends IService<Car> {

    public ResponseResult addCar(Car car);

}
