package com.msb.apidriver.service;

import com.msb.apidriver.remote.ServiceOrderClient;
import com.msb.dao.ResponseResult;
import com.msb.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class OrderService {
    @Autowired
    ServiceOrderClient serviceOrderClient;
    public ResponseResult changeStatus(OrderRequest orderRequest){
        return serviceOrderClient.changeStatus(orderRequest);
    }
    public ResponseResult arrivedDeparture(Long orderId){
        return serviceOrderClient.arrivedDeparture(orderId);
    }
    public ResponseResult pickUpPassenger(OrderRequest orderRequest){
        return serviceOrderClient.pickUpPassenger(orderRequest);
    }
    public ResponseResult passengerGetoff(@RequestBody OrderRequest orderRequest){
        return serviceOrderClient.passengerGetoff(orderRequest);
    }
}
