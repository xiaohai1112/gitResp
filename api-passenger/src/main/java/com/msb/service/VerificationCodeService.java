package com.msb.service;

import com.msb.dao.ResponseResult;
import com.msb.remote.VerificationCodeClient;
import com.msb.responese.NumberCodeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class VerificationCodeService {
    @Autowired
    private VerificationCodeClient verificationCodeClient;
    public Map generatorCode(String PassengerPhone){
        //调用验证码服务，获取验证码
        System.out.println("调用验证码服务，获取验证码");
        ResponseResult<NumberCodeResponse> numberCode = verificationCodeClient.getNumberCode(6);
        int numberCode1 = numberCode.getData().getNumberCode();

        System.out.println("获取到的验证码"+numberCode1);

        System.out.println("存入redis");
        Map map=new HashMap();
        map.put("code",1);
        map.put("msg","success");
        return map;
    }
}
