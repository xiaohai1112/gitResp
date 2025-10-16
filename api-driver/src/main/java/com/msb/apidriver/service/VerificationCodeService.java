package com.msb.apidriver.service;

import com.msb.dao.ResponseResult;
import com.msb.request.VerificationCodeDTO;
import org.springframework.stereotype.Service;

@Service
public class VerificationCodeService {
    public ResponseResult checkAndsendVerificationCode(String driverPhone){
        //查询有没有这个手机号
        //获取验证码
        //调用第三方发送验证码
        //存入redis
        return ResponseResult.success("");
    }
}
