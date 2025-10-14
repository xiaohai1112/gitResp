package com.msb.servicemap.service;

import com.msb.constant.UrlDirectionConstant;
import com.msb.dao.ResponseResult;
import com.msb.servicemap.remote.MapDistritctClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DicDistrictService {
    @Autowired
    private MapDistritctClient mapDistritctClient;
    public ResponseResult dicDistrict(String keywords){
        //请求地图
        String district = mapDistritctClient.district(keywords);
        System.out.println(district);

        //解析结果
        //插入数据库
        return ResponseResult.success();
    }
}
