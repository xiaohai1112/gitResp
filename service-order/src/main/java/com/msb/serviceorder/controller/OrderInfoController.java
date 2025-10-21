package com.msb.serviceorder.controller;


import com.msb.constant.HttpParamCommon;
import com.msb.dao.ResponseResult;
import com.msb.request.OrderRequest;
import com.msb.serviceorder.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author child
 * @since 2025-10-20
 */
@RestController
@RequestMapping("/order")
public class OrderInfoController {
    @Autowired
    OrderInfoService orderInfoService;
    @PostMapping("/add")
    public ResponseResult add(@RequestBody OrderRequest orderRequest, HttpServletRequest request){
        //成功
//        String deviceCode = request.getHeader(HttpParamCommon.DEVICE_CODE);
//        orderRequest.setDeviceCode(deviceCode);
        return orderInfoService.add(orderRequest);
    }
}
