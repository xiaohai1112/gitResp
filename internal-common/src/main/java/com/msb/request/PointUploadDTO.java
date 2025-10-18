package com.msb.request;

import lombok.Data;

@Data
public class PointUploadDTO {
    private String tid;
    private String trid;
    private PointDTO[] points;
}
