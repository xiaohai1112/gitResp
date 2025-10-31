package com.msb.servicemap.remote;

import com.msb.constant.UrlDirectionConstant;
import com.msb.dao.ResponseResult;
import com.msb.responese.ServiceResponse;
import com.msb.responese.TerminalResponse;
import com.msb.responese.TracksResponese;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TerminalClient {
    @Value("${amap.key}")
    private String key;
    @Value("${amap.sid}")
    private String sid;
    @Autowired
    private RestTemplate restTemplate;
    public ResponseResult add(String name,String desc){
        //拼接url
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(UrlDirectionConstant.TERMINAL_URL);
        stringBuilder.append("key="+key);
        stringBuilder.append("&");
        stringBuilder.append("sid="+sid);
        stringBuilder.append("&");
        stringBuilder.append("name="+name);
        stringBuilder.append("&");
        stringBuilder.append("desc="+desc);
        /**
         *这是get请求  .getForEntity
         * post请求应用  .postForEntity
         */
        log.info("创建端口请求："+stringBuilder.toString());
        ResponseEntity<String> forEntity = restTemplate.postForEntity(stringBuilder.toString(),null, String.class);
        String body = forEntity.getBody();
        log.info("创建端口响应："+body);
        JSONObject jsonObject = JSONObject.fromObject(body);
        JSONObject data = jsonObject.getJSONObject("data");
        String tid = data.getString("tid");
        TerminalResponse terminalResponse = new TerminalResponse();
        terminalResponse.setTid(tid);

        return ResponseResult.success(terminalResponse);
    }
    public ResponseResult<List<TerminalResponse>> aroundsearch(String center,Integer radius){
        //拼接url
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(UrlDirectionConstant.ARPUNDSEARCH_URI);
        stringBuilder.append("key="+key);
        stringBuilder.append("&");
        stringBuilder.append("sid="+sid);
        stringBuilder.append("&");
        stringBuilder.append("center="+center);
        stringBuilder.append("&");
        stringBuilder.append("radius="+radius);
        /**
         *这是get请求  .getForEntity
         * post请求应用  .postForEntity
         */
        System.out.println("搜索周围车辆请求："+stringBuilder.toString());
        ResponseEntity<String> forEntity = restTemplate.postForEntity(stringBuilder.toString(),null, String.class);
        String body = forEntity.getBody();
        System.out.println("搜索周围车辆响应："+body);
        JSONArray results = JSONObject.fromObject(body).getJSONObject("data").getJSONArray("results");
        List<TerminalResponse> list=new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            TerminalResponse terminalResponse = new TerminalResponse();
            JSONObject jsonObject = results.getJSONObject(i);
            String tid = jsonObject.getString("tid");
            String desc = jsonObject.getString("desc");//强转会丢失经度 string--> Long
            Long carId = Long.parseLong(desc);

            JSONObject location = jsonObject.getJSONObject("location");
            String latitude = location.getString("latitude");
            String longitude = location.getString("longitude");
            terminalResponse.setLatitude(latitude);
            terminalResponse.setLongitude(longitude);

            terminalResponse.setTid(tid);
            terminalResponse.setCarId(carId);
            list.add(terminalResponse);
        }
        return ResponseResult.success(list);
    }
    public ResponseResult<TracksResponese> trsearch(String tid,Long starttime,Long endtime){
        //拼接url
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(UrlDirectionConstant.TRSEARCH_URI);
        stringBuilder.append("key="+key);
        stringBuilder.append("&");
        stringBuilder.append("sid="+sid);
        stringBuilder.append("&");
        stringBuilder.append("tid="+tid);
        stringBuilder.append("&");
        stringBuilder.append("starttime="+starttime);
        stringBuilder.append("&");
        stringBuilder.append("endtime="+endtime);
        /**
         *这是get请求  .getForEntity
         * post请求应用  .postForEntity
         */
        log.info("创建端口请求："+stringBuilder.toString());
        ResponseEntity<String> forEntity = restTemplate.postForEntity(stringBuilder.toString(),null, String.class);
        String body = forEntity.getBody();
        log.info("创建端口响应："+body);
        JSONObject data = JSONObject.fromObject(body).getJSONObject("data");
        int counts = data.getInt("counts");
        if (counts==0){
            return null;
        }
        JSONArray tracks = data.getJSONArray("tracks");
        Long driveMile=0L;
        Long driveTime=0L;
        for (int i=0;i<tracks.size();i++){
            JSONObject jsonObject = tracks.getJSONObject(i);
            long distance = jsonObject.getLong("distance");
            driveMile+=distance;
            long time = jsonObject.getLong("time");
            time=time/(1000*60);
            driveTime+=time;
        }
        TracksResponese tracksResponese = new TracksResponese();
        tracksResponese.setDriveMile(driveMile);
        tracksResponese.setDriveTime(driveTime);
        return ResponseResult.success(tracksResponese);
    }
}
