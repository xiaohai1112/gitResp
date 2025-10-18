package com.msb.servicemap.contorller;

import com.msb.dao.ResponseResult;
import com.msb.request.PointUploadDTO;
import com.msb.servicemap.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PointController {
    @Autowired
    private PointService pointService;
    @PostMapping("/point/upload")
    public ResponseResult uplocd(@RequestBody PointUploadDTO pointUploadDTO){
        return pointService.upload(pointUploadDTO);
    }
}
