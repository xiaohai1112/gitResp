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
     * 用户不存在1200-1299 exist
     */
    USER_NOT_EXIST(1299,"用户不存在"),
    /**
     * 计价规则不存在1300-1399
     */
    PICE_RULE_NOT_EXIST(1300,"计价规则不存在"),
    PICE_RULE_EXIST(1301,"计价规则存在"),
    PICE_RULE_NO_CHANGE(1302,"计价规则无变化"),
    PICE_RULE_NOT_NEW(1304,"计价规则不是最新"),
    /**
     * 司机 车辆 错误信息提醒   1400-1499
     */
    DRIVER_BIND_EXIST(1400,"司机已被绑定"),
    CAR_BIND_EXIST(1402,"车辆已被绑定"),
    DRIVER_CAR_BIND_EXIST(1403,"司机和车辆已被绑定，请勿重复绑定"),
    DRIVER_CAR_BIND_NOT_EXIST(1404,"司机与车辆关系不存在"),
    DRIVER_NOT_EXIST(1405,"司机不存在"),
    /**
     * 订单   1500-1599
     */
    ORDER_EXIST(1500,"还有正在运行的订单，请订单结束后，再下单"),
    LIMIT_ORDERS(1501,"服务异常不允许下单"),
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
