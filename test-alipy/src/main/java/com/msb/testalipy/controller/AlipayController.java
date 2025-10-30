package com.msb.testalipy.controller;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@ResponseBody
@RequestMapping("/alipay")
public class AlipayController {
    @GetMapping("/pay")
    public String pay(String subject,String outTradeNo,String totalAmount){
        AlipayTradePagePayResponse response = null;
        try {
            response= Factory.Payment.Page().pay(subject,outTradeNo,totalAmount,"");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response.getBody();
    }
    @PostMapping("/notify")
    public String notify(HttpServletRequest request) throws Exception {
        System.out.println("支付宝回调");
        String tradeStatus = request.getParameter("trade_status");
        if (tradeStatus.trim().equals("TRADE_SUCCESS")){
            Map<String,String> map=new HashMap<>();
            Map<String, String[]> parameterMap = request.getParameterMap();
            for (String name:parameterMap.keySet()){
                map.put(name,request.getParameter(name));
            }
            if (Factory.Payment.Common().verifyNotify(map)){
                for (String name:map.keySet()){
                    System.out.println("接收到的参数：");
                    System.out.println(name+","+map.get(name));
                }
            }
        }
        return "success";
    }
}
