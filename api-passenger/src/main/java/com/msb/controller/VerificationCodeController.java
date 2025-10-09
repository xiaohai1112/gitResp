package com.msb.controller;

import com.msb.dao.ResponseResult;
import com.msb.request.VerificationCodeDTO;
import com.msb.service.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
public class VerificationCodeController {
    @Autowired
    private VerificationCodeService verificationCodeService;
    @GetMapping("/verification-code")
    @ResponseBody
    public ResponseResult verificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO){
        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        System.out.println("接收到的号码是："+passengerPhone);
        return verificationCodeService.generatorCode(passengerPhone);
    }
    @PostMapping("/verification-code-check")
    public ResponseResult checkverificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO){
        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        String verificationCode = verificationCodeDTO.getVerificationCode();

        System.out.println(passengerPhone+"----"+verificationCode);

        return verificationCodeService.checkCode(passengerPhone,verificationCode);
    }
}
