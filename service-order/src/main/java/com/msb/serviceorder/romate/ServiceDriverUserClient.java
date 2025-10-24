package com.msb.serviceorder.romate;

import com.msb.dao.ResponseResult;
import com.msb.responese.OrderResponse;
import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("service-driver-user")
public interface ServiceDriverUserClient {
    @RequestMapping(method = RequestMethod.GET,value = "/city-driver/count")
    public ResponseResult<Boolean> find(@RequestParam String cityCode);
    @RequestMapping(method = RequestMethod.GET,value = "/get-available-driver/{carId}")
    public ResponseResult<OrderResponse> getAvailableDriver(@PathVariable Long carId);
}
