package com.ssw331.hospital.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * @author WWWsy
 */
@Data
@Getter
@TableName("prescription")
public class Prescription {
    @TableId("prescription_id")
    private String prescriptionId;
    @TableField("total_price")
    private BigDecimal totalPrice;
    @TableField("doctor_id")
    private String doctorId;
    @TableField("paystate")
    private BigDecimal payState;
}
