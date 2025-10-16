package com.msb.servicedriveruser.controller;


import com.msb.dao.DriverCarBindingRelationship;
import com.msb.dao.ResponseResult;
import com.msb.servicedriveruser.service.DriverCarBindingRelationshipService;
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
 * @since 2025-10-16
 */
@RestController
public class DriverCarBindingRelationshipController {
    @Autowired
    private DriverCarBindingRelationshipService driverCarBindingRelationshipService;
    @PostMapping("/driver_car_binding_relationship/bind")
    public ResponseResult add(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship){
        return driverCarBindingRelationshipService.addDriverCarBindingRelationship(driverCarBindingRelationship);
    }
    @PostMapping("/driver_car_binding_relationship/unbind")
    public ResponseResult update(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship){
        return driverCarBindingRelationshipService.updateDriverCarBindingRelationship(driverCarBindingRelationship);
    }
}
