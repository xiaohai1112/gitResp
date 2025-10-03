package com.msb.controller;

import com.msb.request.VerificationCodeDTO;
import com.msb.service.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class VerificationCodeController {
    @Autowired
    private VerificationCodeService verificationCodeService;
    @RequestMapping("/verification-code")
    @ResponseBody
    public Map verificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO){
        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        System.out.println("接收到的号码是："+passengerPhone);
        return verificationCodeService.generatorCode(passengerPhone);
    }
}
