package com.msb.servicemap.remote;

import com.msb.constant.UrlDirectionConstant;
import com.msb.dao.ResponseResult;
import com.msb.responese.ServiceResponse;
import com.msb.responese.TerminalResponse;
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
        System.out.println("创建端口请求："+stringBuilder.toString());
        ResponseEntity<String> forEntity = restTemplate.postForEntity(stringBuilder.toString(),null, String.class);
        String body = forEntity.getBody();
        System.out.println("创建端口响应："+body);
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
            Long carId = jsonObject.getLong("desc");
            terminalResponse.setTid(tid);
            terminalResponse.setCarId(carId);
            list.add(terminalResponse);
        }
        return ResponseResult.success(list);
    }
}
