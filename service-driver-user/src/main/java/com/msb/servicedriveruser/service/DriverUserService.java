package com.msb.servicedriveruser.service;

import com.msb.constant.CommonStatusEnum;
import com.msb.constant.DriverCarConstant;
import com.msb.dao.DriverUser;
import com.msb.dao.DriverUserWorkStatus;
import com.msb.dao.ResponseResult;
import com.msb.servicedriveruser.mapper.DriverUserMapper;
import com.msb.servicedriveruser.mapper.DriverUserWorkStatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class DriverUserService {
    @Autowired
    private DriverUserMapper driverUserMapper;
    @Autowired
    private DriverUserWorkStatusMapper driverUserWorkStatusMapper;
    public ResponseResult test(){
        return ResponseResult.success(driverUserMapper.selectById(1));
    }
    public ResponseResult addDriverUser(DriverUser driverUser){
        LocalDateTime now = LocalDateTime.now();
        driverUser.setGmtCreate(now);
        driverUser.setGmtModified(now);
        driverUserMapper.insert(driverUser);
        //初始化 司机状态表
        DriverUserWorkStatus driverUserWorkStatus = new DriverUserWorkStatus();
        driverUserWorkStatus.setDriverId(driverUser.getId());
        driverUserWorkStatus.setWorkStatus(DriverCarConstant.DRIVER_WORK_STATUS_STOP);
        driverUserWorkStatus.setGmtCreate(now);
        driverUserWorkStatus.setGmtModified(now);
        driverUserWorkStatusMapper.insert(driverUserWorkStatus);
        return ResponseResult.success("");
    }
    public ResponseResult updateDriverUser(DriverUser driverUser){
        LocalDateTime now=LocalDateTime.now();
        driverUser.setGmtModified(now);
        driverUserMapper.updateById(driverUser);
        return ResponseResult.success();
    }
    public ResponseResult<DriverUser> findDriverUserByPhone(String driverPhone){
        Map<String,Object> map=new HashMap<>();
        map.put("driver_phone",driverPhone);
        map.put("state", DriverCarConstant.DRIVER_STATE_VALID);
        List<DriverUser> driverUsers = driverUserMapper.selectByMap(map);
        if (driverUsers.isEmpty()){
            return ResponseResult.fail(CommonStatusEnum.DRIVER_NOT_EXIST.getCode(),CommonStatusEnum.DRIVER_NOT_EXIST.getValue());
        }
        DriverUser driverUser = driverUsers.get(0);
        return ResponseResult.success(driverUser);
    }
}
