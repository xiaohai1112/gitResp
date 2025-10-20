package com.msb.responese;

import lombok.Data;

@Data
public class ForecastPriceResponese {
    private double price;
    private String cityCode;
    private String vehicleType;
}
