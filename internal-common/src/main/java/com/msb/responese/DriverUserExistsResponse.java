package com.msb.responese;

import lombok.Data;

@Data
public class DriverUserExistsResponse {
    private String driverPhone;
    private Integer isExist;
}
