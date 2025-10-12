package com.msb.Utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.msb.dao.TokenResponse;
import com.msb.request.TokenRequest;

import javax.xml.crypto.Data;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {
    public static final String SING="WZS@1112&*^%";
    public static final String JWT_KEY_PHONE="phone";
    public static final String JWT_KEY_IDENTY="identy";//身份
    public static final String JWT_TOKEN_TYPE="tokenType";//token的类型
    //生成Token
    public static String generatorToken(String passengerPhone,String identy,String tokenType){
        Map<String,String> map =new HashMap<>();
        map.put(JWT_KEY_PHONE,passengerPhone);
        map.put(JWT_KEY_IDENTY,identy);
        map.put(JWT_TOKEN_TYPE,tokenType);
        //设置token过期时间
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.DATE,1);
        Date date=calendar.getTime();

        JWTCreator.Builder builder = JWT.create();
        //整合map
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        //整合时间
//        builder.withExpiresAt(date);
        String sign = builder.sign(Algorithm.HMAC256(SING));
        return sign;
    }
    //解析token
    public static TokenRequest parseToken(String token){
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
        String phone = verify.getClaim(JWT_KEY_PHONE).asString();
        String identy = verify.getClaim(JWT_KEY_IDENTY).asString();

        TokenRequest tokenRequest = new TokenRequest();
        tokenRequest.setPhone(phone);
        tokenRequest.setIdenty(identy);
        return tokenRequest;
    }

    /**
     * 校验token，主要看token是否正确
     * @param token
     * @return
     */
    public static TokenRequest checkToken(String token){
        TokenRequest tokenRequest=null;
        try {
            tokenRequest = JwtUtils.parseToken(token);
        }catch (Exception e){

        }
        return null;
    }

    public static void main(String[] args) {
//        Map<String,String> map=new HashMap<>();
//        map.put("name","Rose");
//        map.put("age","18");
        String s = generatorToken("15825893654","1","accessToken");
        System.out.println("生成的token:"+s);
        System.out.println("解析----------------------");
        TokenRequest tokenRequest = parseToken(s);
        System.out.println("手机号："+tokenRequest.getPhone());
        System.out.println("身份："+tokenRequest.getIdenty());
    }
}
