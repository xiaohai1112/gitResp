package com.msb.servicedriveruser.controller;


import com.msb.dao.DriverUserWorkStatus;
import com.msb.dao.ResponseResult;
import com.msb.servicedriveruser.service.DriverUserWorkStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author child
 * @since 2025-10-17
 */
@RestController
public class DriverUserWorkStatusController {
    @Autowired
    private DriverUserWorkStatusService driverUserWorkStatusService;
    @PostMapping("/driver_user_work_status")
    public ResponseResult update(@RequestBody DriverUserWorkStatus driverUserWorkStatus){
        return driverUserWorkStatusService.updateDriverUserWorkStatus(driverUserWorkStatus.getDriverId(),driverUserWorkStatus.getWorkStatus());
    }
}
