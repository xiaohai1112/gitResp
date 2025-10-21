package com.msb.Utils;

public class RedisPrefixUtils {
    //验证码前缀
    public static String verificationCodePrefix="verification-code";
    //token前缀
    public static String tokenPrefix="token-";
    //黑名单前缀
    public static String deviceCodePrefix="deviceCode-";

    /**
     * 根据手机号生成key
     * @param phone
     * @param identy
     * @return
     */

    public static String generatorKeyPhone(String phone,String identy){
        return verificationCodePrefix +"-"+identy+"-"+ phone;
    }

    /**
     * 根据手机号和身份标识  生成token
     * @param passengerPhone
     * @param identy
     * @param tokenType
     * @return
     */
    public static String generatorKeyToken(String passengerPhone,String identy,String tokenType){
        return tokenPrefix+"-"+passengerPhone+"-"+identy+"-"+tokenType;
    }
}
