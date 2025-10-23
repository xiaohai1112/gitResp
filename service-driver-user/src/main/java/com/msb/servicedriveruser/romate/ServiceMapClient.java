package com.msb.servicedriveruser.romate;

import com.msb.dao.ResponseResult;
import com.msb.responese.TerminalResponse;
import com.msb.responese.TrackResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("service-map")
public interface ServiceMapClient {
    @RequestMapping(method = RequestMethod.POST,value = "/terminal/add")
    public ResponseResult<TerminalResponse> addTerminal(@RequestParam("name") String name,@RequestParam("desc") String desc);
    @RequestMapping(method = RequestMethod.POST,value = "/trace/add")
    public ResponseResult<TrackResponse> addTrace(@RequestParam String tid);
}
