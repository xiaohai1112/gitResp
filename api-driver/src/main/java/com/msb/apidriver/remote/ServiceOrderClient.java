package com.msb.apidriver.remote;

import com.msb.dao.ResponseResult;
import com.msb.request.OrderRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("service-order")
public interface ServiceOrderClient {
    //司机去接乘客
    @RequestMapping(method = RequestMethod.POST,value = "/order/changeStatus")
    public ResponseResult changeStatus(@RequestBody OrderRequest orderRequest);
    //到达乘客上车点
    @RequestMapping(method = RequestMethod.POST,value = "/order/arrivedDeparture")
    public ResponseResult arrivedDeparture(@RequestParam Long orderId);
    //接到乘客
    @RequestMapping(method = RequestMethod.POST,value = "/order/pickUpPassenger")
    public ResponseResult pickUpPassenger(@RequestBody OrderRequest orderRequest);
    //行程结束到达目的地
    @RequestMapping(method = RequestMethod.POST,value = "/order/passengerGetoff")
    public ResponseResult passengerGetoff(@RequestBody OrderRequest orderRequest);
    //取消订单
    @RequestMapping(method = RequestMethod.POST,value = "/order/cancel")
    public ResponseResult cancel(@RequestParam Long orderId, @RequestParam String identy);
}
