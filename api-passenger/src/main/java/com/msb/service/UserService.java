package com.msb.service;

import com.msb.Utils.JwtUtils;
import com.msb.dao.PassengerUser;
import com.msb.dao.ResponseResult;
import com.msb.remote.ServicePassengerUserClient;
import com.msb.request.TokenRequest;
import com.msb.request.VerificationCodeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    @Autowired
    private ServicePassengerUserClient servicePassengerUserClient;
    public ResponseResult getUserByAccessToken(String accessToken){
        log.info("accessToken"+accessToken);
        //解析token,获取手机号
        TokenRequest tokenRequest = JwtUtils.parseToken(accessToken);
        String phone = tokenRequest.getPhone();
        log.info("手机号"+phone);
        //根据手机号查用户信息
        ResponseResult<PassengerUser> userByPhone = servicePassengerUserClient.getUserByPhone(phone);
//        PassengerUser passengerUser = new PassengerUser();
//        passengerUser.setPassengerName("rose");
//        passengerUser.setProfilePhoto("头像");
        return ResponseResult.success(userByPhone.getData());
    }
}
