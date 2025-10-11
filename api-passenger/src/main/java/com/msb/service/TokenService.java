package com.msb.service;

import com.msb.dao.ResponseResult;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    public ResponseResult refreshToken(String refreshToken){
        //解析refreshToken

        //redis中取出refreshToken

        //校验refreshToken

        //生成双token
        return null;
    }
}
