package com.msb.service;

import com.msb.constant.IdentyConstant;
import com.msb.dao.ResponseResult;
import com.msb.remote.ServiceOrderClient;
import com.msb.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class OrderService {
    @Autowired
    private ServiceOrderClient serviceOrderClient;
    public ResponseResult add(OrderRequest orderRequest){
        return serviceOrderClient.add(orderRequest);
    }
    public ResponseResult cancel(Long orderId){
        return serviceOrderClient.cancel(orderId, IdentyConstant.IDENTY_A);
    }
}
