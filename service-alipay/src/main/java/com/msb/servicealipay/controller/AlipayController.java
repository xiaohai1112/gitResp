package com.msb.servicealipay.controller;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import com.msb.servicealipay.service.AlipayService;
import org.springframework.beans.factory.annotation.Autowired;
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
//    @Autowired
//    AlipayService alipayService;
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
                System.out.println("----------------");
//                String s = map.get("out_trade_no");
//                Long orderId=Long.parseLong(s);
//                System.out.println("支付宝回调"+orderId);
//                alipayService.pay(orderId);
            }
        }else {
            System.out.println("支付宝验证 不通过！");
        }
        return "success";
    }
}
