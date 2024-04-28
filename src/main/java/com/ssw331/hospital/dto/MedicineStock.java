package com.ssw331.hospital.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author WWWsy
 */
@Getter
@Data
@TableName("medicine_stock")
public class MedicineStock {
    private String id;
    @TableField("medicine_name")
    private String medicineName;
    @TableField("manufacturer")
    private String manufacturer;
    @TableField("production_date")
    private LocalDateTime productionDate;
    @TableField("medicine_Shelflife")
    private BigDecimal medicineShelflife;
    @TableField("medicine_Amount")
    private BigDecimal medicineAmount;
    @TableField("threshold_Value")
    private BigDecimal thresholdValue;
    @TableField("clean_Date")
    private LocalDateTime cleanDate;
    @TableField("clean_administrator")
    private String cleanAdministrator;

}