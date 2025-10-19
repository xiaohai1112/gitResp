package com.msb.apidriver.remote;

import com.msb.dao.ResponseResult;
import com.msb.request.PointUploadDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("service-map")
public interface ServiceMapClient {
    @RequestMapping(method = RequestMethod.POST,value = "/point/upload")
    public ResponseResult upload(@RequestBody PointUploadDTO pointUploadDTO);
}
