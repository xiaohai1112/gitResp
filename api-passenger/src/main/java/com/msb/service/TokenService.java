package com.msb.service;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.msb.Utils.JwtUtils;
import com.msb.Utils.RedisPrefixUtils;
import com.msb.constant.CommonStatusEnum;
import com.msb.constant.TokenConstant;
import com.msb.dao.ResponseResult;
import com.msb.responese.TokenResponse;
import com.msb.request.TokenRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TokenService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    public ResponseResult refreshToken(String refreshTokenSrc){
        //解析refreshToken
        TokenRequest tokenRequest = JwtUtils.parseToken(refreshTokenSrc);
        if (refreshTokenSrc==null){
            return ResponseResult.fail(CommonStatusEnum.TOKEN_ERROR.getCode(),CommonStatusEnum.TOKEN_ERROR.getValue());
        }
        String phone = tokenRequest.getPhone();
        String identy = tokenRequest.getIdenty();
        //redis中取出refreshToken
        String refreshTokenKey = RedisPrefixUtils.generatorKeyToken(phone, identy, TokenConstant.REFRESH_TOKEN_TYPE);
        String refreshTokenRedis = stringRedisTemplate.opsForValue().get(refreshTokenKey);
        //校验refreshToken
        if ((StringUtils.isBlank(refreshTokenRedis)) || (!refreshTokenSrc.trim().equals(refreshTokenRedis.trim())) ){
            return ResponseResult.fail(CommonStatusEnum.TOKEN_ERROR.getCode(),CommonStatusEnum.TOKEN_ERROR.getValue());
        }
        //生成双token
        String accessToken = JwtUtils.generatorToken(phone, identy, TokenConstant.ACCESS_TOKEN_TYPE);
        String refreshToken = JwtUtils.generatorToken(phone, identy, TokenConstant.ACCESS_TOKEN_TYPE);

        String accessTokenKey = RedisPrefixUtils.generatorKeyToken(phone, identy, TokenConstant.ACCESS_TOKEN_TYPE);
        stringRedisTemplate.opsForValue().set(accessTokenKey,accessToken,30, TimeUnit.DAYS);
        stringRedisTemplate.opsForValue().set(refreshTokenKey,refreshToken,35,TimeUnit.DAYS);


        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setAccessToken(accessToken);
        tokenResponse.setRefreshToken(refreshToken);
        return ResponseResult.success(tokenResponse);
    }
}
