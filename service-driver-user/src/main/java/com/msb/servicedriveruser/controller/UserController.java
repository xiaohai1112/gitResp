package com.msb.servicedriveruser.controller;

import com.msb.constant.DriverCarConstant;
import com.msb.dao.DriverUser;
import com.msb.dao.ResponseResult;
import com.msb.responese.DriverUserExistsResponse;
import com.msb.servicedriveruser.service.DriverUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private DriverUserService driverUserService;
    @PostMapping("/user")
    public ResponseResult addUser(@RequestBody DriverUser driverUser){
        return driverUserService.addDriverUser(driverUser);
    }
    @PutMapping("/user")
    public ResponseResult updateUser(@RequestBody DriverUser driverUser){
        return driverUserService.updateDriverUser(driverUser);
    }

    /**
     * 根据手机号查询司机
     * @param driverPhone
     * @return
     */
    @GetMapping("/check-driver/{driverPhone}")
    public ResponseResult<DriverUserExistsResponse> find(@PathVariable("driverPhone") String driverPhone){
        ResponseResult<DriverUser> driverUserByPhone = driverUserService.findDriverUserByPhone(driverPhone);
        DriverUser driverUserByPhonedb = driverUserByPhone.getData();
        DriverUserExistsResponse response = new DriverUserExistsResponse();
        int isExist = DriverCarConstant.DRIVER_EXIST;
        if (driverUserByPhonedb==null){
            isExist = DriverCarConstant.DRIVER_NO_EXIST;
            response.setDriverPhone(driverPhone);
            response.setIsExist(isExist);
        }else{
            response.setDriverPhone(driverUserByPhonedb.getDriverPhone());
            response.setIsExist(isExist);
        }
        return ResponseResult.success(response);
    }
    @GetMapping("/get-available-driver/{carId}")
    public ResponseResult getAvailableDriver(@PathVariable Long carId){
       return driverUserService.getAvailableDriver(carId);
    }
}
