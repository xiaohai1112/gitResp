package com.msb.servicemap.remote;

import com.msb.constant.UrlDirectionConstant;
import com.msb.responese.DistanceResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MapDirectionClient {
    private static final Logger log = LoggerFactory.getLogger(MapDirectionClient.class);
    @Autowired
    private RestTemplate restTemplate;
    @Value("${amap.key}")
    private String key;

    public DistanceResponse direction(String depLongitude,String depLatitude,String destLongitude,String destLatitude){
        //组装请求url
        /**
         * output=json&key=0a02ecd651de9640e9f6ab2c3fd2f5ef
         */
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(UrlDirectionConstant.DIRECTION_URL);
        urlBuilder.append("origin="+depLongitude+","+depLatitude);
        urlBuilder.append("&");
        urlBuilder.append("destination="+destLongitude+","+destLatitude);
        urlBuilder.append("&");
        urlBuilder.append("extensions=base");
        urlBuilder.append("&");
        urlBuilder.append("output=json");
        urlBuilder.append("&");
        urlBuilder.append("key="+key);
        log.info(urlBuilder.toString());
        //调用高德接口
        ResponseEntity<String> directionEntity = restTemplate.getForEntity(urlBuilder.toString(), String.class);
        String directionString = directionEntity.getBody();
        log.info("高德地图，路径规划，返回信息"+directionString);
        //解析接口
        DistanceResponse distanceResponse = parseDirectionEntity(directionString);

        return distanceResponse;
    }
    private DistanceResponse parseDirectionEntity(String directionString){
        DistanceResponse distanceResponse = null;
        try {
            JSONObject result = JSONObject.fromObject(directionString);
            if (result.has(UrlDirectionConstant.STATUS)){
                int status = result.getInt(UrlDirectionConstant.STATUS);
                if(status==1){
                    if (result.has(UrlDirectionConstant.ROUTE)){
                        JSONObject routeObject = result.getJSONObject(UrlDirectionConstant.ROUTE);
                        JSONArray pathsArray = routeObject.getJSONArray(UrlDirectionConstant.PATHS);
                        JSONObject pathsObject = pathsArray.getJSONObject(0);//取第一条数据
                        distanceResponse = new DistanceResponse();

                        if (pathsObject.has(UrlDirectionConstant.DISTANCE)){
                            int distance = pathsObject.getInt(UrlDirectionConstant.DISTANCE);
                            distanceResponse.setDistance(distance);
                        }
                        if (pathsObject.has(UrlDirectionConstant.DURATION)){
                            int duration = pathsObject.getInt(UrlDirectionConstant.DURATION);
                            distanceResponse.setDuration(duration);
                        }
                    }
                }
            }

        }catch (Exception e){

        }

        return distanceResponse;
    }
}
