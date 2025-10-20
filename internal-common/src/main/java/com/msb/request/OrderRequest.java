package com.msb.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class OrderRequest {
    //地区行政代码
    private String address;
    //订单编号
    private String orderId;
    //预计用车时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime departTime;
    //订单发起时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderTime;
    //出发地
    private String departure;
    //出发地经纬度
    private String depLongitude;
    private String depLatitude;
    //目的地
    private String destination;
    //目的地经纬度
    private String destLongitude;
    private String destLatitude;
    //坐标加密标识
    private Integer encrypt;
    //运价类型编码
    private String fareType;
}
