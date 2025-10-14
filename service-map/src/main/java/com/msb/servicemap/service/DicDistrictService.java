package com.msb.servicemap.service;

import com.msb.constant.UrlDirectionConstant;
import com.msb.dao.ResponseResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DicDistrictService {
    @Value("${amap.key}")
    private String key;
    public ResponseResult district(String keywords){
        //拼接url
        //keywords=北京&subdistrict=2&key=<用户的key>
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(UrlDirectionConstant.DISTRICT_URL);
        stringBuilder.append("keywords="+keywords);
        stringBuilder.append("&");
        stringBuilder.append("subdistrict=3");
        stringBuilder.append("&");
        stringBuilder.append("key="+key);
        return ResponseResult.success();
    }
}
