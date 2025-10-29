package com.msb.apidriver.service;

import com.msb.apidriver.remote.SsepushClient;
import com.msb.constant.IdentyConstant;
import com.msb.dao.ResponseResult;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayService {
    @Autowired
    SsepushClient ssepushClient;
    public ResponseResult payMoneyInfo(Long orderId,Long passengerId,String price){
        //封装消息
        JSONObject message = new JSONObject();
        message.put("price",price);
        //推送消息
        ssepushClient.push(passengerId, IdentyConstant.IDENTY_A,message.toString());

        return ResponseResult.success();
    }
}
