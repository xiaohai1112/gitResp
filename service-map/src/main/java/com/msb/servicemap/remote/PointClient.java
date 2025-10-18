package com.msb.servicemap.remote;

import com.msb.constant.UrlDirectionConstant;
import com.msb.dao.ResponseResult;
import com.msb.request.PointDTO;
import com.msb.request.PointUploadDTO;
import com.msb.responese.TerminalResponse;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URL;

@Service
public class PointClient {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${amap.key}")
    private String key;
    @Value("${amap.sid}")
    private String sid;
    public ResponseResult upload(PointUploadDTO pointUploadDTO){
        //拼接url
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(UrlDirectionConstant.POINT_URI);
        stringBuilder.append("key="+key);
        stringBuilder.append("&");
        stringBuilder.append("sid="+sid);
        stringBuilder.append("&");
        stringBuilder.append("tid="+pointUploadDTO.getTid());
        stringBuilder.append("&");
        stringBuilder.append("trid="+pointUploadDTO.getTrid());
        stringBuilder.append("&");
        stringBuilder.append("points=");
        PointDTO[] points = pointUploadDTO.getPoints();
        stringBuilder.append("%5B");
        for (PointDTO p:points){
            stringBuilder.append("%7B");
            String location = p.getLocation();
            String locatetime = p.getLocatetime();
            stringBuilder.append("%22location%22");
            stringBuilder.append("%3A");
            stringBuilder.append("%22"+location+"%22");
            stringBuilder.append("%2C");
            stringBuilder.append("%22locatetime%22");
            stringBuilder.append("%3A");
            stringBuilder.append(locatetime);
            stringBuilder.append("%7D");
        }
        stringBuilder.append("%5D");
        /**
         *这是get请求  .getForEntity
         * post请求应用  .postForEntity
         */
        System.out.println("高德地图请求"+stringBuilder.toString());
        ResponseEntity<String> forEntity = restTemplate.postForEntity(URI.create(stringBuilder.toString()),null, String.class);
        String body = forEntity.getBody();
        System.out.println("高德地图响应"+body);
        return ResponseResult.success();
    }
}
