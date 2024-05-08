package com.ssw331.hospital.dto;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

/**
 * @author 24053
 */
@Getter
@Setter
@Data
@Table("outpatient_order")
public class OutPatientOrder {
    @TableId("ORDER_ID")
    private String orderId;
    @TableField("PATIENT_ID")
    private String patientId;
    @TableField("ORDER_TIME")
    private Timestamp orderTime;
}
