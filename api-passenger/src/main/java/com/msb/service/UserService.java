package com.msb.service;

import com.msb.Utils.JwtUtils;
import com.msb.dao.PassengerUser;
import com.msb.dao.ResponseResult;
import com.msb.request.TokenRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    public ResponseResult getUserByAccessToken(String accessToken){
        log.info("accessToken"+accessToken);
        //解析token,获取手机号
        TokenRequest tokenRequest = JwtUtils.parseToken(accessToken);
        PassengerUser passengerUser = new PassengerUser();
        passengerUser.setPassengerName("rose");
        passengerUser.setProfilePhoto("头像");
        return ResponseResult.success(passengerUser);
    }
}
