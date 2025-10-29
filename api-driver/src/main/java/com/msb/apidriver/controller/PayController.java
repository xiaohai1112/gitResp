package com.msb.apidriver.controller;

import com.msb.apidriver.service.PayService;
import com.msb.dao.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pay")
public class PayController {
    @Autowired
    private PayService payService;
    @PostMapping("/pay-money-info")
    public ResponseResult payMoneyInfo(@RequestParam Long orderId,@RequestParam Long passengerId,@RequestParam String price){
        return payService.payMoneyInfo(orderId,passengerId,price);
    }
}
