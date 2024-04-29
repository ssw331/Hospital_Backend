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
@Data
@Getter
@Table("treatment_info")
public class DiagnosedHistory {
    @TableId
    private String diagnoseId;
    @TableField
    private Timestamp diagnoseTime;
    @TableField
    private BigDecimal commentState;
    @TableField
    private String selfReported;
    @TableField
    private String presentHis;
    @TableField
    private String anamnesis;
    @TableField
    private String sign;
    @TableField
    private String clinicDia;
    @TableField
    private String advice;
    @TableField
    private BigDecimal kindQuantity;
}
