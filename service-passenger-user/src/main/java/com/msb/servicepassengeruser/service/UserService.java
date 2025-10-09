package com.msb.servicepassengeruser.service;

import com.msb.dao.ResponseResult;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public ResponseResult loginOrRegister(String passengerPhone){
        System.out.println("user service 被调用");
        //获取手机号
        //根据手机号插入用户
        //返回成功
        return ResponseResult.success();
    }
}
