package com.msb.request;

import lombok.Data;

@Data
public class VerificationCodeDTO {
    private String PassengerPhone;
    private String verificationCode;

}
