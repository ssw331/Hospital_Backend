package com.ssw331.hospital.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author 24053
 */
@Getter
@Data
@Table("consultation_info")
public class ConsultationInfo {
    @TableField("DOCTOR_ID")
    private String doctorId;
    @TableField("CLINIC_NAME")
    private String clinicName;
    @TableField("DATE_TIME")
    private Timestamp dateTime;
    @TableField("PERIOD")
    private BigDecimal period;
}
