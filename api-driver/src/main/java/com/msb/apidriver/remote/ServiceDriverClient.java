package com.msb.apidriver.remote;

import com.msb.dao.DriverUser;
import com.msb.dao.ResponseResult;
import com.msb.responese.DriverUserExistsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient("service-driver-user")
public interface ServiceDriverClient {
    @RequestMapping(method = RequestMethod.PUT,value = "/user")
    public ResponseResult updateDriver(@RequestBody DriverUser driverUser);
    @RequestMapping(method = RequestMethod.GET,value = "/check-driver/{driverPhone}")
    public ResponseResult<DriverUserExistsResponse> findDriverByPhone(@PathVariable String driverPhone);
}
