package com.msb.apidriver.service;

import com.alibaba.nacos.common.utils.StringUtils;
import com.msb.Utils.JwtUtils;
import com.msb.Utils.RedisPrefixUtils;
import com.msb.apidriver.remote.ServiceDriverClient;
import com.msb.apidriver.remote.ServiceVerificationCodeClient;
import com.msb.constant.CommonStatusEnum;
import com.msb.constant.DriverCarConstant;
import com.msb.constant.IdentyConstant;
import com.msb.constant.TokenConstant;
import com.msb.dao.ResponseResult;
import com.msb.request.VerificationCodeDTO;
import com.msb.responese.DriverUserExistsResponse;
import com.msb.responese.NumberCodeResponse;
import com.msb.responese.TokenResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class VerificationCodeService {
    @Autowired
    ServiceDriverClient serviceDriverClient;
    @Autowired
    ServiceVerificationCodeClient serviceVerificationCodeClient;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    public ResponseResult checkAndsendVerificationCode(String driverPhone){
        //查询有没有这个手机号
        ResponseResult<DriverUserExistsResponse> driverByPhone = serviceDriverClient.findDriverByPhone(driverPhone);
        DriverUserExistsResponse data = driverByPhone.getData();
        Integer isExist = data.getIsExist();
        if (isExist!= DriverCarConstant.DRIVER_EXIST){
            return ResponseResult.fail(CommonStatusEnum.DRIVER_NOT_EXIST.getCode(),CommonStatusEnum.DRIVER_NOT_EXIST.getValue());
        }
        log.info(driverPhone+"司机存在");
        //获取验证码
        ResponseResult<NumberCodeResponse> numberCodeResult = serviceVerificationCodeClient.getVerificationCode(6);
        NumberCodeResponse numberCodeResponse = numberCodeResult.getData();
        int verificationCode = numberCodeResponse.getNumberCode();
        log.info("验证码:"+verificationCode);
        //调用第三方发送验证码

        //存入redis  --》 1.生成key 2：放入value
        String key = RedisPrefixUtils.generatorKeyPhone(driverPhone, IdentyConstant.IDENTY_B);
        stringRedisTemplate.opsForValue().set(key,verificationCode+"",2, TimeUnit.MINUTES);
        return ResponseResult.success("");
    }
    /**
     * 校验验证码
     * @param driverPhone 手机号
     * @param verificationCode 验证码
     * @return
     */
    public ResponseResult checkCode(String driverPhone,String verificationCode){
        //根据手机号从redis中读取验证码
        System.out.println("根据手机号从redis中读取验证码");
        //生成key
        String key= RedisPrefixUtils.generatorKeyPhone(driverPhone,IdentyConstant.IDENTY_B);
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

        //发令牌
        String accessToken = JwtUtils.generatorToken(driverPhone, IdentyConstant.IDENTY_B, TokenConstant.ACCESS_TOKEN_TYPE);
        String refreshToken = JwtUtils.generatorToken(driverPhone, IdentyConstant.IDENTY_B, TokenConstant.REFRESH_TOKEN_TYPE);

        //将token存入redis
        String accessTokenkey = RedisPrefixUtils.generatorKeyToken(driverPhone, IdentyConstant.IDENTY_B,TokenConstant.ACCESS_TOKEN_TYPE);
        stringRedisTemplate.opsForValue().set(accessTokenkey,accessToken,30,TimeUnit.DAYS);
        String refreshTokenkey = RedisPrefixUtils.generatorKeyToken(driverPhone, IdentyConstant.IDENTY_B,TokenConstant.REFRESH_TOKEN_TYPE);
        stringRedisTemplate.opsForValue().set(refreshTokenkey,refreshToken,35,TimeUnit.DAYS);


        //返回token
        System.out.println("返回token");
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setAccessToken(accessToken);
        tokenResponse.setRefreshToken(refreshToken);
        return ResponseResult.success(tokenResponse);
    }
}
