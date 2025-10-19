package com.msb.request;

import lombok.Data;

@Data
public class ApiPointRequest {
    private Long carId;
    private PointDTO[] points;
}
