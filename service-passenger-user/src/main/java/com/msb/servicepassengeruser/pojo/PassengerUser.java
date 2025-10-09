package com.msb.servicepassengeruser.pojo;

import lombok.Data;

import java.util.Date;
@Data
public class PassengerUser {
    private Long id;
    private Date gmtCreate;
    private Date gmtModified;
    private String passengerPhone;
    private String passengerName;
    private byte passengerGender;
    private byte state;
}
