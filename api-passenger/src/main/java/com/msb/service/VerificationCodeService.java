package com.msb.service;


import com.alibaba.nacos.common.utils.StringUtils;
import com.msb.constant.CommonStatusEnum;
import com.msb.dao.ResponseResult;
import com.msb.dao.TokenResponse;
import com.msb.remote.VerificationCodeClient;
import com.msb.responese.NumberCodeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class VerificationCodeService {
    //验证码前缀
    private String verificationCodePrefix="passenger-verification-code";
    @Autowired
    private VerificationCodeClient verificationCodeClient;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 生成验证码
     * @param passengerPhone 手机号
     * @return
     */
    public ResponseResult generatorCode(String passengerPhone){
        //调用验证码服务，获取验证码
        System.out.println("调用验证码服务，获取验证码");
        ResponseResult<NumberCodeResponse> numberCode = verificationCodeClient.getNumberCode(6);
        int numberCode1 = numberCode.getData().getNumberCode();

        System.out.println("获取到的验证码"+numberCode1);

        System.out.println("存入redis");
        //设置key、value、ttl（过期时间）
        String key = generatorKeyPhone(passengerPhone);
        //存入redis
        stringRedisTemplate.opsForValue().set(key,numberCode1+"",2, TimeUnit.MINUTES);


        //不想data为null值，可以传一个“”字符串  eg:ResponseResult.success("");
        return ResponseResult.success();
    }

    /**
     * 根据手机号生成key
     * @param passengerPhone
     * @return
     */

    public String generatorKeyPhone(String passengerPhone){
        return verificationCodePrefix + passengerPhone;
    }

    /**
     * 校验验证码
     * @param passengerPhone 手机号
     * @param verificationCode 验证码
     * @return
     */
    public ResponseResult checkCode(String passengerPhone,String verificationCode){
        //根据手机号从redis中读取验证码
        System.out.println("根据手机号从redis中读取验证码");
        //生成key
        String key=generatorKeyPhone(passengerPhone);
        //校验验证码
        System.out.println("校验验证码");
        //根据key取value
        String codeRedis = stringRedisTemplate.opsForValue().get(key);
        System.out.println("redis中的value:"+codeRedis);

        if (StringUtils.isBlank(codeRedis)){
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(),CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }
        if (!verificationCode.trim().equals(codeRedis.trim())){
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(),CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }

        //判断原来是否有用户，并处理
        System.out.println("判断原来是否有用户，并处理");
        //返回token
        System.out.println("返回token");
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken("token value");
        return ResponseResult.success(tokenResponse);
    }
}
