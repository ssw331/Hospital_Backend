package com.ssw331.hospital.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
@Table(name = "consultation_info")
public class ConsultationInfo {
// 这里的TableId是用于测试是否影响功能，如果不影响实际功能的话就不用动了
    @TableId("DOCTOR_ID")
    private String doctorId;
    @TableField("CLINIC_NAME")
    private String clinicName;
    @TableField("DATE_TIME")
    private Timestamp dateTime;
    @TableField("PERIOD")
    private BigDecimal period;
}
