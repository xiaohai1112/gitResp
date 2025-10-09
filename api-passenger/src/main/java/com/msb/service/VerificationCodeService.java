package com.msb.service;

import com.msb.dao.ResponseResult;
import com.msb.remote.VerificationCodeClient;
import com.msb.responese.NumberCodeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class VerificationCodeService {
    //验证码前缀
    private String verificationCodePrefix="passenger-verification-code";
    @Autowired
    private VerificationCodeClient verificationCodeClient;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    public ResponseResult generatorCode(String PassengerPhone){
        //调用验证码服务，获取验证码
        System.out.println("调用验证码服务，获取验证码");
        ResponseResult<NumberCodeResponse> numberCode = verificationCodeClient.getNumberCode(6);
        int numberCode1 = numberCode.getData().getNumberCode();

        System.out.println("获取到的验证码"+numberCode1);

        System.out.println("存入redis");
        //设置key、value、ttl（过期时间）
        String key = verificationCodePrefix + PassengerPhone;
        //存入redis
        stringRedisTemplate.opsForValue().set(key,numberCode1+"",2, TimeUnit.MINUTES);


        //不想data为null值，可以传一个“”字符串  eg:ResponseResult.success("");
        return ResponseResult.success();
    }
}
