package com.msb.Utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import javax.xml.crypto.Data;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {
    public static final String SING="WZS@1112&*^%";
    public static final String JWT_KEY="passengerPhone";
    //生成Token
    public static String generatorToken(String passengerPhone){
        Map<String,String> map =new HashMap<>();
        map.put(JWT_KEY,passengerPhone);
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
        builder.withExpiresAt(date);
        String sign = builder.sign(Algorithm.HMAC256(SING));
        return sign;
    }
    //解析token
    public static String parseToken(String token){
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
        Claim claim = verify.getClaim(JWT_KEY);
        return claim.toString();
    }

    public static void main(String[] args) {
//        Map<String,String> map=new HashMap<>();
//        map.put("name","Rose");
//        map.put("age","18");
        String s = generatorToken("15825893654");
        System.out.println("生成的token:"+s);
        System.out.println("解析的token:"+parseToken(s));
    }
}
