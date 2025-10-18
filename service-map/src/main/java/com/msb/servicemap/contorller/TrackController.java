package com.msb.servicemap.contorller;

import com.msb.dao.ResponseResult;
import com.msb.servicemap.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrackController {
    @Autowired
    private TrackService trackService;
    @PostMapping("/trace/add")
    public ResponseResult add(String tid){
        return trackService.add(tid);
    }
}
