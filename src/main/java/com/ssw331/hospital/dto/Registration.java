package com.ssw331.hospital.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;


import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author WWWsy
 */
@Data
@Getter
@TableName("registration")
public class Registration {
    @TableField("patient_id")
    private String patientId;
    @TableField("doctor_id")
    private String doctorId;
    @TableField("appointment_time")
    private Date appointmentTime;
    @TableField("period")
    private BigDecimal period;
    @TableField("registOrder")
    private BigDecimal registOrder;
    @TableField("state")
    private BigDecimal state;
    @TableField("prescriptionId")
    private String prescriptionId;
    @TableField("checkIn")
    private BigDecimal checkIn;
    @TableField("qrCodeUrl")
    private String qrCodeUrl;
    @TableField("orderTime")
    private Timestamp orderTime;

}
