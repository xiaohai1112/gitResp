package com.msb.serviceorder.controller;


import com.msb.dao.ResponseResult;
import com.msb.request.OrderRequest;
import com.msb.serviceorder.service.OrderInfoService;
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
 * @since 2025-10-20
 */
@RestController
@RequestMapping("/order")
public class OrderInfoController {
    @Autowired
    OrderInfoService orderInfoService;
    @PostMapping("/add")
    public ResponseResult add(@RequestBody OrderRequest orderRequest){
        System.out.println("order:"+orderRequest.getAddress());
        return orderInfoService.add(orderRequest);
    }
}
