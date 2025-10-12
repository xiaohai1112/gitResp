package com.msb.interceptor;


import com.alibaba.cloud.commons.lang.StringUtils;
import com.msb.Utils.JwtUtils;
import com.msb.Utils.RedisPrefixUtils;
import com.msb.constant.TokenConstant;
import com.msb.dao.ResponseResult;

import com.msb.request.TokenRequest;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;


public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean result =true;
        String resultString = "";
        String token = request.getHeader("Authorization");
        //解析token
        TokenRequest tokenRequest = JwtUtils.checkToken(token);


        if (tokenRequest==null){
            resultString="token inval";
            result = false;
        }else {
            //拼接key
            String phone = tokenRequest.getPhone();
            String identy = tokenRequest.getIdenty();
            String tokenKey = RedisPrefixUtils.generatorKeyToken(phone, identy, TokenConstant.ACCESS_TOKEN_TYPE);
            //从redis中取出token
            String tokenRdis = stringRedisTemplate.opsForValue().get(tokenKey);
            if ((StringUtils.isBlank(tokenRdis)) || (!token.trim().equals(tokenRdis.trim())) ){
                resultString="token inval";
                result = false;
            }
        }
        
        if (!result){
            PrintWriter out =response.getWriter();
            out.print(JSONObject.fromObject(ResponseResult.fail(resultString)).toString());
        }

        return result;
    }
}
