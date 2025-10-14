package com.msb.servicemap.remote;

import com.msb.constant.UrlDirectionConstant;
import com.msb.dao.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MapDistritctClient {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${amap.key}")
    private String key;
    public String district(String keywords){
        //拼接url
        //keywords=北京&subdistrict=2&key=<用户的key>
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(UrlDirectionConstant.DISTRICT_URL);
        stringBuilder.append("keywords="+keywords);
        stringBuilder.append("&");
        stringBuilder.append("subdistrict=3");
        stringBuilder.append("&");
        stringBuilder.append("key="+key);
        ResponseEntity<String> forEntity = restTemplate.getForEntity(stringBuilder.toString(), String.class);
        return forEntity.getBody();
    }
}
