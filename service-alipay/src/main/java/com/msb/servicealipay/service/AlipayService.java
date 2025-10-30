package com.msb.servicealipay.service;

import com.msb.dao.ResponseResult;
import com.msb.request.OrderRequest;
import com.msb.servicealipay.romate.SeviceOrderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlipayService {
    @Autowired
    private SeviceOrderClient seviceOrderClient;
    public ResponseResult pay(Long orderId){
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setOrderId(orderId);
        return seviceOrderClient.pay(orderRequest);
    }
}
