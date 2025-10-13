package com.msb.serviceprice.romate;

import com.msb.dao.ResponseResult;
import com.msb.request.ForecastPriceDTO;
import com.msb.responese.DistanceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@FeignClient("service-map")
public interface ServiceMapClient {
    @RequestMapping(method = RequestMethod.GET,value = "/direction/driving")
    public ResponseResult<DistanceResponse> direction(@RequestBody ForecastPriceDTO forecastPriceDTO);
}
