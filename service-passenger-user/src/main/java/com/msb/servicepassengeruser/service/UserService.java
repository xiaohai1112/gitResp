package com.msb.servicepassengeruser.service;


import com.msb.dao.ResponseResult;
import com.msb.servicepassengeruser.mapper.UserMapper;
import com.msb.servicepassengeruser.pojo.PassengerUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
        if (passengerUserList.size()==0){
            PassengerUser puser = new PassengerUser();
            puser.setPassengerName("菲菲");
            puser.setPassengerPhone(passengerPhone);
            puser.setPassengerGender((byte) 0);
            puser.setState((byte) 0);

            LocalDateTime now=LocalDateTime.now();//比java.util.date线程更安全
            // LocalDateTime → Timestamp → Date
            Timestamp timestamp = Timestamp.valueOf(now);
            puser.setGmtCreate(timestamp);
            puser.setGmtModified(timestamp);

            userMapper.insert(puser);
        }
        //返回成功
        return ResponseResult.success();
    }
}
