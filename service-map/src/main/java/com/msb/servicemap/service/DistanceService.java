package com.msb.servicemap.service;

import com.msb.dao.ResponseResult;
import com.msb.responese.DistanceResponse;
import com.msb.servicemap.remote.MapDirectionClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DistanceService {
    @Autowired
    private MapDirectionClient mapDirectionClient;
    public ResponseResult driving(String depLongitude, String depLatitude, String destLongitude, String destLatitude){
        //调用第三方地图
        DistanceResponse direction = mapDirectionClient.direction(depLongitude, depLatitude, destLongitude, destLatitude);
        return ResponseResult.success(direction);
    }
}
