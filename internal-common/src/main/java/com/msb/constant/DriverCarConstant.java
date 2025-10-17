package com.msb.constant;

public class DriverCarConstant {
    /**
     * 司机和车辆绑定
     */
    public static final int DRIVER_CAR_BIND=1;
    /**
     * 司机和车辆解绑
     */
    public static final int DRIVER_CAR_UNBIND=2;
    /**
     * 司机状态  有效
     */
    public static final int DRIVER_STATE_VALID=1;
    /**
     * 司机状态  无效
     */
    public static final int DRIVER_STATE_INVALID=0;
    /**
     * 司机 0：不存在 ，1：存在
     */
    public static final int DRIVER_NO_EXIST=0;
    public static final int DRIVER_EXIST=1;
    /**
     * 出车
     */
    public static final int DRIVER_WORK_STATUS_START=1;
    /**
     * 暂停接单
     */
    public static final int DRIVER_WORK_STATUS_PAUSE=2;
    /**
     * 收车
     */
    public static final int DRIVER_WORK_STATUS_STOP=0;
}
