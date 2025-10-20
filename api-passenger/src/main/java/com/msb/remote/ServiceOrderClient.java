package com.msb.remote;

import com.msb.dao.ResponseResult;
import com.msb.request.OrderRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("service-order")
public interface ServiceOrderClient {
    @RequestMapping(method = RequestMethod.POST,value = "order/add")
    public ResponseResult add(@RequestBody OrderRequest orderRequest);
}
