package com.msb.apidriver.controller;

import com.msb.apidriver.service.PointService;
import com.msb.dao.ResponseResult;
import com.msb.request.ApiPointRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/point")
public class PointController {
    @Autowired
    private PointService pointService;
    @PostMapping("/upload")
    public ResponseResult upload(@RequestBody ApiPointRequest apiPointRequest){
        return pointService.upload(apiPointRequest);
    }
}
