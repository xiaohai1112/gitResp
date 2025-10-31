package com.msb.responese;

import lombok.Data;

@Data
public class TerminalResponse {
    private String tid;
    private Long carId;
    private String latitude;
    private String longitude;
}
