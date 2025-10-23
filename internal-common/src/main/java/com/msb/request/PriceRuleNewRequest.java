package com.msb.request;

import lombok.Data;

@Data
public class PriceRuleNewRequest {
    private String fareType;
    private Integer fareVersion;
}
