package com.msb.servicemap.remote;

import com.msb.constant.UrlDirectionConstant;
import com.msb.dao.ResponseResult;
import com.msb.responese.ServiceResponse;
import com.msb.responese.TerminalResponse;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TerminalClient {
    @Value("${amap.key}")
    private String key;
    @Value("${amap.sid}")
    private String sid;
    @Autowired
    private RestTemplate restTemplate;
    public ResponseResult add(String name){
        //拼接url
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(UrlDirectionConstant.TERMINAL_URL);
        stringBuilder.append("key="+key);
        stringBuilder.append("&");
        stringBuilder.append("sid="+sid);
        stringBuilder.append("&");
        stringBuilder.append("name="+name);
        /**
         *这是get请求  .getForEntity
         * post请求应用  .postForEntity
         */
        ResponseEntity<String> forEntity = restTemplate.postForEntity(stringBuilder.toString(),null, String.class);
        String body = forEntity.getBody();
        JSONObject jsonObject = JSONObject.fromObject(body);
        JSONObject data = jsonObject.getJSONObject("data");
        String tid = data.getString("tid");
        TerminalResponse terminalResponse = new TerminalResponse();
        terminalResponse.setTid(tid);
        return ResponseResult.success(terminalResponse);
    }
}
