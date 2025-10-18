package com.msb.servicemap.service;

import com.msb.dao.ResponseResult;
import com.msb.request.PointUploadDTO;
import com.msb.servicemap.remote.PointClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PointService {
    @Autowired
    private PointClient pointClient;
    public ResponseResult upload(PointUploadDTO pointUploadDTO){
        return pointClient.upload(pointUploadDTO);
    }
}
