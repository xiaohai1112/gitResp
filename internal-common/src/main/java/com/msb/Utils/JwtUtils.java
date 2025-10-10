package com.msb.Utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;

import javax.xml.crypto.Data;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {
    public static final String SING="WZS@1112&*^%";
    //生成Token
    public static String generatorToken(Map<String,String> map){
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

    public static void main(String[] args) {
        Map<String,String> map=new HashMap<>();
        map.put("name","Rose");
        map.put("age","18");
        String s = generatorToken(map);
        System.out.println("生成的token:"+s);
    }
}
