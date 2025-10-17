package com.msb.apidriver.service;

import com.msb.apidriver.remote.ServiceDriverClient;
import com.msb.constant.CommonStatusEnum;
import com.msb.constant.DriverCarConstant;
import com.msb.dao.ResponseResult;
import com.msb.responese.DriverUserExistsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VerificationCodeService {
    @Autowired
    ServiceDriverClient serviceDriverClient;
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

        //调用第三方发送验证码
        //存入redis
        return ResponseResult.success("");
    }
}
