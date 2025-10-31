package com.msb.apidriver.service;

import com.msb.apidriver.remote.ServiceOrderClient;
import com.msb.apidriver.remote.SsepushClient;
import com.msb.constant.IdentyConstant;
import com.msb.dao.ResponseResult;
import com.msb.request.OrderRequest;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayService {
    @Autowired
    SsepushClient ssepushClient;
    @Autowired
    ServiceOrderClient serviceOrderClient;
    public ResponseResult payMoneyInfo(Long orderId,Long passengerId,String price){
        //封装消息
        JSONObject message = new JSONObject();
        message.put("price",price);
        message.put("orderId",orderId);
        //修改订单状态
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setOrderId(orderId);
        serviceOrderClient.pushPayInfo(orderRequest);
        //推送消息
        ssepushClient.push(passengerId, IdentyConstant.IDENTY_A,message.toString());

        return ResponseResult.success();
    }
}
