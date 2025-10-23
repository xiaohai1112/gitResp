package com.msb.servicemap.remote;

import com.msb.constant.UrlDirectionConstant;
import com.msb.dao.ResponseResult;
import com.msb.responese.TrackResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class TrackClient {
    @Value("${amap.key}")
    private String key;
    @Value("${amap.sid}")
    private String sid;
    @Autowired
    private RestTemplate restTemplate;
    public ResponseResult add(String tid) {

        //拼接url
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(UrlDirectionConstant.TRACK_URL);
        stringBuilder.append("key=" + key);
        stringBuilder.append("&");
        stringBuilder.append("sid=" + sid);
        stringBuilder.append("&");
        stringBuilder.append("tid=" + tid);
        /**
         *这是get请求  .getForEntity
         * post请求应用  .postForEntity
         */
        log.info("创建轨迹请求"+stringBuilder.toString());
        ResponseEntity<String> forEntity = restTemplate.postForEntity(stringBuilder.toString(), null, String.class);
        String body = forEntity.getBody();
        log.info("创建轨迹响应"+body);
        JSONObject jsonObject = JSONObject.fromObject(body);
        int errcode = jsonObject.getInt("errcode");
        if (errcode!=10000){
            return ResponseResult.fail("出现错误！！！！！");
        }
        JSONObject data = jsonObject.getJSONObject("data");
        String trid = data.getString("trid");
        String trname = "";
        if (data.has("trname")){
            trname=data.getString("trname");
        }
        TrackResponse trackResponse = new TrackResponse();
        trackResponse.setTrid(trid);
        trackResponse.setTrname(trname);
        return ResponseResult.success(trackResponse);
    }
}
