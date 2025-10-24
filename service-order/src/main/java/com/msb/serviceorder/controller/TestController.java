package com.msb.serviceorder.controller;

import com.msb.dao.OrderInfo;
import com.msb.serviceorder.mapper.OrderInfoMapper;
import com.msb.serviceorder.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @RequestMapping("/test")
    public String test(){
        return "service-order";
    }
    @Autowired
    OrderInfoService orderInfoService;
    @Autowired
    OrderInfoMapper orderInfoMapper;

    @GetMapping("/test-real-time-order/{orderId}")
    public String dispatchRealTimeOrder(@PathVariable("orderId") long orderId){
        System.out.println(" 并发测试：orderId："+orderId);
        OrderInfo orderInfo = orderInfoMapper.selectById(orderId);
        orderInfoService.aroundsearch(orderInfo);
        return "test-real-time-order   success";
    }

}
