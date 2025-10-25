package com.msb.controller;

import com.msb.dao.ResponseResult;
import com.msb.remote.ServiceOrderClient;
import com.msb.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {
    @RequestMapping("/test")
    public String test01(){
        return "test.......";
    }
    @GetMapping("/authTest")
    public ResponseResult authTest(){
        return ResponseResult.success("auth 1");
    }
    @GetMapping("/noauthTest")
    public ResponseResult noAuthTest(){
        return ResponseResult.success("no auth 1");
    }
    @Autowired
    ServiceOrderClient serviceOrderClient;
    @GetMapping("/test-real-time-order/{orderId}")
    public String dispatchRealTimeOrder(@PathVariable("orderId") long orderId) {
        System.out.println("并发测试：orderId：" + orderId);
        serviceOrderClient.dispatchRealTimeOrder(orderId);
        return "";
    }
}
