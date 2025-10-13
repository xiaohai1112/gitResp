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
    /**
     * 用户不存在1120-1299 exist
     */
    USER_NOT_EXIST(1299,"用户不存在"),
    /**
     * 计价规则不存在1300-1399
     */
    PICE_RULE_NOT_EXIST(1300,"计价规则不存在"),
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
