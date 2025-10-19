package com.msb.servicedriveruser.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.msb.dao.Car;
import com.msb.dao.ResponseResult;
import com.msb.responese.TerminalResponse;
import com.msb.responese.TrackResponse;
import com.msb.servicedriveruser.mapper.CarMapper;
import com.msb.servicedriveruser.romate.ServiceMapClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
        //获取tid
        ResponseResult<TerminalResponse> terminalResponseResponseResult = serviceMapClient.addTerminal(car.getVehicleNo());
        String tid = terminalResponseResponseResult.getData().getTid();
        car.setTid(tid);
        //获取trid
        ResponseResult<TrackResponse> trackResponseResponseResult = serviceMapClient.addTrace(tid);
        String trid = trackResponseResponseResult.getData().getTrid();
        String trname = trackResponseResponseResult.getData().getTrname();
        car.setTrid(trid);
        car.setTrname(trname);
        carMapper.insert(car);
        return ResponseResult.success("");
    }

    public ResponseResult<Car> getCarById(Long id){
        Map<String, Object> map=new HashMap<>();
        map.put("id",id);
        List<Car> cars = carMapper.selectByMap(map);
        return ResponseResult.success(cars.get(0));
    }

}
