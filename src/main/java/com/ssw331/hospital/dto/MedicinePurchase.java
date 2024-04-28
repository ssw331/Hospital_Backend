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
@TableName("medicine_purchase")
public class MedicinePurchase {
    private String id;
    @TableField("medicine_Name")
    private String medicineName;
    @TableField("manufacturer")
    private String manufacturer;
    @TableField("production_Date")
    private LocalDateTime productionDate;
    @TableField("purchase_Date")
    private LocalDateTime purchaseDate;
    @TableField("administrator_id")
    private String administratorId;
    @TableField("purchase_Amount")
    private BigDecimal purchaseAmount;
    @TableField("purchase_Price")
    private BigDecimal purchasePrice;


}