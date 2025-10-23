package com.msb.serviceorder.romate;

import com.msb.dao.PriceRule;
import com.msb.dao.ResponseResult;
import com.msb.request.PriceRuleNewRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("service-price")
public interface ServicePriceClient {
    @RequestMapping(method = RequestMethod.POST,value = "/price/is-latest-version")
    public ResponseResult<Boolean> isLatestVersion(@RequestBody PriceRuleNewRequest priceRuleNewRequest);
    @RequestMapping(method = RequestMethod.POST,value = "/price/is-exits")
    public ResponseResult<Boolean> isExits(@RequestBody PriceRule priceRule);
}
