package com.msb.dao;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author child
 * @since 2025-10-17
 */
@Data
public class DriverUserWorkStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private Long driverId;

    private Integer workStatus;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;

}
