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
@TableName("prescription_medicine")
public class PrescriptionMedicine {
    @TableId("prescription_id")
    private String prescriptionId;
    @TableField("medicine_name")
    private String medicineName;
    @TableField("medication_instruction")
    private String medicationInstruction;
    @TableField("medicine_price")
    private BigDecimal medicinePrice;
    @TableField("quantity")
    private BigDecimal quantity;

}
