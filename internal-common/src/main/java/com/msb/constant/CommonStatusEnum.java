package com.msb.constant;

import lombok.Getter;

public enum CommonStatusEnum {
    /**
     * token错误提示信息1100-1199
     */
    TOKEN_ERROR(1199,"token错误"),
    /**
     * 验证码错误提示信息 1000-1099
     */
    VERIFICATION_CODE_ERROR(1099,"验证码错误"),
    SUCCESS(1,"success"),
    FAIL(0,"fail");
    @Getter
    private int code;
    @Getter
    private String value;

    CommonStatusEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }
}
