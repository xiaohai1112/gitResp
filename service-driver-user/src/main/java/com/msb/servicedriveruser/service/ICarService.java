package com.msb.servicedriveruser.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.msb.dao.Car;
import com.msb.dao.ResponseResult;
import com.msb.responese.TerminalResponse;
import com.msb.servicedriveruser.mapper.CarMapper;
import com.msb.servicedriveruser.romate.ServiceMapClient;
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
    @Autowired
    private ServiceMapClient serviceMapClient;
    public ResponseResult addCar(Car car) {
        LocalDateTime now=LocalDateTime.now();
        car.setGmtCreate(now);
        car.setGmtModified(now);
        ResponseResult<TerminalResponse> terminalResponseResponseResult = serviceMapClient.addTerminal(car.getVehicleNo());
        String tid = terminalResponseResponseResult.getData().getTid();
        car.setTid(tid);
        carMapper.insert(car);
        return ResponseResult.success("");
    }

}
