package com.msb.serviceorder.controller;


import com.msb.constant.HttpParamCommon;
import com.msb.dao.ResponseResult;
import com.msb.request.OrderRequest;
import com.msb.serviceorder.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

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
        String deviceCode = request.getHeader(HttpParamCommon.DEVICE_CODE);
        orderRequest.setDeviceCode(deviceCode);
        return orderInfoService.add(orderRequest);
    }
    //司机去接乘客
    @PostMapping("/changeStatus")
    public ResponseResult changeStatus(@RequestBody OrderRequest orderRequest){
        return orderInfoService.toPickUpPassenger(orderRequest);
    }
    //到达乘客上车点
    @PostMapping("/arrivedDeparture")
    public ResponseResult arrivedDeparture(@RequestParam Long orderId){
        return orderInfoService.arrivedDeparture(orderId);
    }
    //接到乘客
    @PostMapping("/pickUpPassenger")
    public ResponseResult pickUpPassenger(@RequestBody OrderRequest orderRequest){
        return orderInfoService.pickUpPassenger(orderRequest);
    }
    //行程结束到达目的地
    @PostMapping("/passengerGetoff")
    public ResponseResult passengerGetoff(@RequestBody OrderRequest orderRequest){
        return orderInfoService.passengerGetoff(orderRequest);
    }
    @PostMapping("/pay")
    public ResponseResult pay(@RequestBody OrderRequest orderRequest){
        return orderInfoService.pay(orderRequest);
    }
    @PostMapping("/cancel")
    public ResponseResult cancel(@RequestParam Long orderId,@RequestParam String identy){
        return orderInfoService.cancel(orderId,identy);
    }
}
