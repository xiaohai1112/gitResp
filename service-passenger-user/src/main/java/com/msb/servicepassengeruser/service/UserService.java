package com.msb.servicepassengeruser.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.msb.dao.ResponseResult;
import com.msb.servicepassengeruser.mapper.UserMapper;
import com.msb.servicepassengeruser.pojo.PassengerUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    public ResponseResult loginOrRegister(String passengerPhone){
        System.out.println("user service 被调用");
//        获取手机号
        Map<String,Object> map=new HashMap<>();
        map.put("passenger_phone",passengerPhone);
        List<PassengerUser> passengerUserList = userMapper.selectByMap(map);
        System.out.println(passengerUserList.size()==0?"无记录":passengerUserList.get(0).getPassengerName());
        //根据手机号插入用户
        //返回成功
        return ResponseResult.success();
    }
}
