package com.msb.apidriver.controller;

import com.msb.Utils.JwtUtils;
import com.msb.apidriver.service.DriverService;
import com.msb.dao.DriverUser;
import com.msb.dao.DriverUserWorkStatus;
import com.msb.dao.ResponseResult;
import com.msb.request.TokenRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class DriverController {
    @Autowired
    private DriverService driverService;
    @PutMapping("/user")
    public ResponseResult update(@RequestBody DriverUser driverUser){
        return driverService.updateUser(driverUser);
    }
    @PostMapping("/driver_user_work_status")
    public ResponseResult update(@RequestBody DriverUserWorkStatus driverUserWorkStatus){
        return driverService.updateWorkStatus(driverUserWorkStatus);
    }
    @GetMapping("/driver_car_binding_relationships")
    public ResponseResult driverCarBindingRelationship(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        TokenRequest tokenRequest = JwtUtils.checkToken(header);
        String phone = tokenRequest.getPhone();
        return driverService.getDriverCarBindingRelationshipByPhone(phone);
    }
}
