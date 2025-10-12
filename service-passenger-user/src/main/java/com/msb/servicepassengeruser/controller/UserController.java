package com.msb.servicepassengeruser.controller;

import com.msb.dao.ResponseResult;
import com.msb.request.VerificationCodeDTO;
import com.msb.servicepassengeruser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/user")
    @ResponseBody
    public ResponseResult login(@RequestBody VerificationCodeDTO verificationCodeDTO){
        String phone = verificationCodeDTO.getPassengerPhone();
        System.out.println("手机号:"+phone);
        return userService.loginOrRegister(phone);

    }
    @GetMapping("/user/{phone}")
    @ResponseBody
    public ResponseResult getUser(@PathVariable("phone") String passengerPhone){
        return userService.getUserByPhone(passengerPhone);
    }
}
