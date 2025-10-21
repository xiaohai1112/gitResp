package com.msb.serviceorder.romate;

import com.msb.dao.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("service-price")
public interface ServicePriceClient {
    @RequestMapping(method = RequestMethod.GET,value = "/price/is-latest-version")
    public ResponseResult<Boolean> isLatestVersion(@RequestParam String fareType, @RequestParam Integer fareVersion);
}
