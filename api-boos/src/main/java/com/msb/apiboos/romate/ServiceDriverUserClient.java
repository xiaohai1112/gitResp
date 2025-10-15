package com.msb.apiboos.romate;

import com.msb.dao.DriverUser;
import com.msb.dao.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("service-driver-user")
public interface ServiceDriverUserClient {
    @RequestMapping(method = RequestMethod.POST,value = "/user")
    public ResponseResult driver(@RequestBody DriverUser driverUser);
}
