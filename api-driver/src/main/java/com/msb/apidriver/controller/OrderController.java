package com.msb.apidriver.controller;

import com.msb.apidriver.service.OrderService;
import com.msb.dao.ResponseResult;
import com.msb.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    //司机去接乘客
    @PostMapping("/changeStatus")
    public ResponseResult changeStatus(@RequestBody OrderRequest orderRequest){
        return orderService.changeStatus(orderRequest);
    }
    //到达乘客上车点
    @PostMapping("/arrivedDeparture")
    public ResponseResult arrivedDeparture(@RequestParam Long orderId){
        return orderService.arrivedDeparture(orderId);
    }
    //接到乘客
    @PostMapping("/pickUpPassenger")
    public ResponseResult pickUpPassenger(@RequestBody OrderRequest orderRequest){
        return orderService.pickUpPassenger(orderRequest);
    }
    //行程结束到达目的地
    @PostMapping("/passengerGetoff")
    public ResponseResult passengerGetoff(@RequestBody OrderRequest orderRequest){
        return orderService.passengerGetoff(orderRequest);
    }
    //quxiaodingd
    @PostMapping("/cancel")
    public ResponseResult cancel(@RequestParam Long orderId){
        return orderService.cancel(orderId);
    }
}
