package com.msb.apidriver.service;

import com.msb.Utils.RedisPrefixUtils;
import com.msb.apidriver.remote.ServiceDriverClient;
import com.msb.apidriver.remote.ServiceVerificationCodeClient;
import com.msb.constant.CommonStatusEnum;
import com.msb.constant.DriverCarConstant;
import com.msb.constant.IdentyConstant;
import com.msb.dao.ResponseResult;
import com.msb.responese.DriverUserExistsResponse;
import com.msb.responese.NumberCodeResponse;
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
}
