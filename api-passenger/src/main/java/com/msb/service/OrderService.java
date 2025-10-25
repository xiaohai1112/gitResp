package com.msb.service;

import com.msb.dao.ResponseResult;
import com.msb.remote.ServiceOrderClient;
import com.msb.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private ServiceOrderClient serviceOrderClient;
    public ResponseResult add(OrderRequest orderRequest){
        return serviceOrderClient.add(orderRequest);
    }
}
