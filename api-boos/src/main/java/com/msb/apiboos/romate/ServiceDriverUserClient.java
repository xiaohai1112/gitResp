package com.msb.apiboos.romate;

import com.msb.dao.Car;
import com.msb.dao.DriverCarBindingRelationship;
import com.msb.dao.DriverUser;
import com.msb.dao.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("service-driver-user")
public interface ServiceDriverUserClient {
    @RequestMapping(method = RequestMethod.POST,value = "/user")
    public ResponseResult addDriver(@RequestBody DriverUser driverUser);
    @RequestMapping(method = RequestMethod.PUT,value = "/user")
    public ResponseResult updateDriver(@RequestBody DriverUser driverUser);
    @RequestMapping(method = RequestMethod.POST,value = "/car")
    public ResponseResult addCar(@RequestBody Car car);
    @RequestMapping(method = RequestMethod.POST,value = "/driver_car_binding_relationship/bind")
    public ResponseResult addDriverCarBindingRelationship(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship);
    @RequestMapping(method = RequestMethod.POST,value = "/driver_car_binding_relationship/unbind")
    public ResponseResult updateDriverCarBindingRelationship(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship);
}
