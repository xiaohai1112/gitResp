package com.msb.apidriver.service;

import com.msb.apidriver.remote.ServiceDriverClient;
import com.msb.apidriver.remote.ServiceMapClient;
import com.msb.dao.Car;
import com.msb.dao.ResponseResult;
import com.msb.request.ApiPointRequest;
import com.msb.request.PointUploadDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PointService {
    @Autowired
    private ServiceDriverClient serviceDriverClient;
    @Autowired
    private ServiceMapClient serviceMapClient;
    public ResponseResult upload(ApiPointRequest apiPointRequest){
        //获取carId
        Long carId = apiPointRequest.getCarId();
        //获取tid、trid 调用service-driver-user
        ResponseResult<Car> carById = serviceDriverClient.getCarById(carId);
        Car car = carById.getData();
        String tid = car.getTid();
        String trid = car.getTrid();
        //调用service-map
        PointUploadDTO pointUploadDTO = new PointUploadDTO();
        pointUploadDTO.setTid(tid);
        pointUploadDTO.setTrid(trid);
        pointUploadDTO.setPoints(apiPointRequest.getPoints());

        return serviceMapClient.upload(pointUploadDTO);
    }
}
