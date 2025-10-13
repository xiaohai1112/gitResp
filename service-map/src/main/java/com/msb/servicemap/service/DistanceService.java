package com.msb.servicemap.service;

import com.msb.dao.ResponseResult;
import com.msb.responese.DistanceResponse;
import org.springframework.stereotype.Service;

@Service
public class DistanceService {
    public ResponseResult driving(String depLongitude,String depLatitude,String destLongitude,String destLatitude){
        DistanceResponse distanceResponse = new DistanceResponse();
        distanceResponse.setDistance(12);
        distanceResponse.setDuration(1);
        return ResponseResult.success(distanceResponse);
    }
}
