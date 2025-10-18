package com.msb.servicemap.remote;

import com.msb.constant.UrlDirectionConstant;
import com.msb.dao.ResponseResult;
import com.msb.responese.ServiceResponse;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class ServiceClient {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${amap.key}")
    private String key;
    public ResponseResult service(String name){
        //拼接url
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(UrlDirectionConstant.SERVICE_URL);
        stringBuilder.append("key="+key);
        stringBuilder.append("&");
        stringBuilder.append("name="+name);
        /**
         *这是get请求  .getForEntity
         * post请求应用  .postForEntity
         */
        ResponseEntity<String> forEntity = restTemplate.postForEntity(stringBuilder.toString(),null, String.class);
        String body = forEntity.getBody();
        JSONObject jsonObject = JSONObject.fromObject(body);
        int i = jsonObject.getInt("errcode");
        if(i!=10000){
            return ResponseResult.fail("错误");
        }
        JSONObject data = jsonObject.getJSONObject("data");
        String sid = data.getString("sid");
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setSid(sid);
        return ResponseResult.success(serviceResponse);
    }
}
