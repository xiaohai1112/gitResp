package com.msb.servicealipay.romate;

import com.msb.dao.ResponseResult;
import com.msb.request.OrderRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("service-order")
public interface SeviceOrderClient {
    @RequestMapping(method = RequestMethod.POST,value = "/order/pay")
    public ResponseResult pay(@RequestBody OrderRequest orderRequest);
}
