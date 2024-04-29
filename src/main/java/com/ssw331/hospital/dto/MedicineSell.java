package com.ssw331.hospital.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * @author WWWsy
 */
@Data
@Getter
@TableName("medicine_sell")
public class MedicineSell {
    @TableField("medicine_name")
    private String medicineName;
    @TableField("manufacturer")
    private String manufacturer;
    @TableField("selling_Price")
    private BigDecimal sellingPrice;
}
