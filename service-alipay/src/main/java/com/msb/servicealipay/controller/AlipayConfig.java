package com.msb.servicealipay.controller;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.Config;
import lombok.Data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
@Data
@Component
//@ConfigurationProperties(prefix = "alipay")
public class AlipayConfig {
    @Value("${alipay.app-id}")
    private String appId;
    @Value("${alipay.app-private-key}")
    private String appPrivateKey;
    @Value("${alipay.public-key}")
    private String publicKey;
    @Value("${alipay.notify-url}")
    private String notifyUrl;

    @PostConstruct
    public void init(){
        Config config = new Config();
        // 基础配置
        config.protocol = "https";
        config.gatewayHost = "openapi-sandbox.dl.alipaydev.com";
        config.signType = "RSA2";

        // 业务配置
        config.appId = this.appId;
        config.merchantPrivateKey = this.appPrivateKey;
        config.alipayPublicKey = this.publicKey;
        config.notifyUrl = this.notifyUrl;

        Factory.setOptions(config);
        System.out.println("支付宝配置初始化完成");
    }
}
