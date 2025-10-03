package com.msb.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class VerificationCodeService {
    public Map generatorCode(String PassengerPhone){
        //调用验证码服务，获取验证码
        System.out.println("调用验证码服务，获取验证码");
        String code="1112";
        System.out.println("存入redis");
        Map map=new HashMap();
        map.put("code",code);
        map.put("msg","success");
        return map;
    }
}
