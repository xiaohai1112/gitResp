package com.msb.controller;

import com.msb.dao.ResponseResult;
import com.msb.request.OrderRequest;
import com.msb.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @PostMapping("/add")
    public ResponseResult add(@RequestBody OrderRequest orderRequest){
        System.out.println(orderRequest);
        return orderService.add(orderRequest);
    }
    @GetMapping("/test-real-time-order/{orderId}")
    public ResponseResult dispatchRealTimeOrder(@PathVariable("orderId") long orderId){
        return orderService.dispatchRealTimeOrder(orderId);
    }
}
