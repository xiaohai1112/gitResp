package com.msb.dao;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class DriverUser {
    // 主键ID
    private Long id;

    // 司机注册地行政区域代码
    private String address;

    // 司机名字
    private String driverName;

    // 司机手机号
    private String driverPhone;

    // 司机性别（1：男，2：女）
    private String driverGender;

    // 出生日期
    private LocalDate driverBrithday;  // 注意：表中字段名是driver_brithday（可能是birthday的拼写错误，建议保持一致）

    // 司机民族
    private String driverNation;

    // 司机通信地址
    private String driverContactAddress;

    // 机动车驾驶证号
    private String licenseId;

    // 初次领取驾驶证的日期
    private LocalDate getDriverLicenseDate;

    // 驾驶证有效期限起
    private LocalDate driverLicenseOn;

    // 驾驶证有效期限止
    private LocalDate driverLicenseOff;

    // 是否巡游出租汽车司机（0：否；1：是）
    private Integer taxiDriver;

    // 网络预约出租车汽车驾驶员资格证号
    private String certificateNo;

    // 网络预约出租车汽车驾驶员证发证机构
    private String networkCarIssueOrganization;

    // 资格证发证日期
    private LocalDate networkCarIssueDate;

    // 初次领取资格证日期
    private LocalDate getNetworkCarProofDate;

    // 资格证有效起始日期
    private LocalDate networkCarProofOn;

    // 资格证有效截止日期
    private LocalDate networkCarProofOff;

    // 报备日期
    private LocalDate registerDate;

    // 商业类型（1:网络预约出租汽车；2：巡游出租汽车；3：私人小客车合乘）
    private Integer commercialType;

    // 驾驶员合同签署公司
    private String contractCompany;

    // 合同有效期起
    private LocalDate contractOn;

    // 合同有效期止
    private LocalDate contractOff;

    // 状态（0:有效 1：失效）
    private Integer state;

    // 操作标识（1:新增；2：更新；3：删除）
    private Integer flag;

    // 创建时间
    private LocalDateTime gmtCreate;

    // 修改时间
    private LocalDateTime gmtModified;
}
