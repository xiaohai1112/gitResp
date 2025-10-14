package com.msb.dao;

import lombok.Data;

@Data
public class DicDistrict {
    private String addressCode;
    private String addressName;
    private String parentAddressCode;
    private String Level;
}
