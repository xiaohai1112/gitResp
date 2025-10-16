package com.msb.apiboos.service;

import com.msb.apiboos.romate.ServiceDriverUserClient;
import com.msb.dao.Car;
import com.msb.dao.DriverCarBindingRelationship;
import com.msb.dao.DriverUser;
import com.msb.dao.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoosService {
    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;
    public ResponseResult addDriver(DriverUser driverUser){
        return ResponseResult.success(serviceDriverUserClient.addDriver(driverUser));
    }
    public ResponseResult updateDriver(DriverUser driverUser){
        return ResponseResult.success(serviceDriverUserClient.updateDriver(driverUser));
    }
    public ResponseResult addCar(Car car){
        return ResponseResult.success(serviceDriverUserClient.addCar(car));
    }
    public ResponseResult addDriverCarBindingRelationship(DriverCarBindingRelationship driverCarBindingRelationship){
        return ResponseResult.success(serviceDriverUserClient.addDriverCarBindingRelationship(driverCarBindingRelationship));
    }
    public ResponseResult updateDriverCarBindingRelationship(DriverCarBindingRelationship driverCarBindingRelationship){
        return ResponseResult.success(serviceDriverUserClient.updateDriverCarBindingRelationship(driverCarBindingRelationship));
    }
}
