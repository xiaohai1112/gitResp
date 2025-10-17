package com.msb.servicedriveruser.service;

import com.msb.dao.DriverUserWorkStatus;
import com.msb.dao.ResponseResult;
import com.msb.servicedriveruser.mapper.DriverUserWorkStatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author child
 * @since 2025-10-17
 */
@Service
public class DriverUserWorkStatusService {
    @Autowired
    private DriverUserWorkStatusMapper driverUserWorkStatusMapper;
    public ResponseResult updateDriverUserWorkStatus(Long driverId,Integer workStatus){
        Map<String, Object> map=new HashMap<>();
        map.put("driver_id",driverId);
        List<DriverUserWorkStatus> driverUserWorkStatuses = driverUserWorkStatusMapper.selectByMap(map);
        DriverUserWorkStatus driverUserWorkStatus = driverUserWorkStatuses.get(0);
        driverUserWorkStatus.setWorkStatus(workStatus);
        driverUserWorkStatusMapper.updateById(driverUserWorkStatus);
        return ResponseResult.success("");
    }
}
