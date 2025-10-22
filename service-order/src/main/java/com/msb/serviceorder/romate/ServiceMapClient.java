package com.msb.serviceorder.romate;

import com.msb.dao.ResponseResult;
import com.msb.responese.TerminalResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("service-map")
public interface ServiceMapClient {
    @RequestMapping(method = RequestMethod.POST,value = "/terminal/aroundsearch")
    public ResponseResult<List<TerminalResponse>> terminalAroundsearch(@RequestParam String center, @RequestParam Integer radius);
}
