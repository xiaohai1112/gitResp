package com.msb.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class OrderRequest {
    //乘客id
    private Long passengerId;
    //乘客手机号
    private String passengerPhone;
    //地区行政代码
    private String address;
    //订单编号
    private Long orderId;
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
    //运价版本号
    private Integer fareVersion;
    //唯一版本号
    private String deviceCode;
    /**
     * 司机去接乘客出发时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime toPickUpPassengerTime;

    /**
     * 去接乘客时，司机的经度
     */
    private String toPickUpPassengerLongitude;

    /**
     * 去接乘客时，司机的纬度
     */
    private String toPickUpPassengerLatitude;

    /**
     * 去接乘客时，司机的地点
     */
    private String toPickUpPassengerAddress;
    /**
     * 接到乘客，乘客上车经度
     */
    private String pickUpPassengerLongitude;

    /**
     * 接到乘客，乘客上车纬度
     */
    private String pickUpPassengerLatitude;
    /**
     * 乘客下车时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime passengerGetoffTime;

    /**
     * 乘客下车经度
     */
    private String passengerGetoffLongitude;

    /**
     * 乘客下车纬度
     */
    private String passengerGetoffLatitude;

    private String vehicleType;


}
