package com.ssw331.hospital.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author 24053
 */
@Data
@Getter
@TableName("treatment_info")
public class DiagnosedHistory {
    @TableId("diagnose_id")
    private String diagnoseId;
    @TableField("diagnose_time")
    private Timestamp diagnoseTime;
    @TableField("commentstate")
    private BigDecimal commentState;
    @TableField("selfReported")
    private String selfReported;
    @TableField("present_His")
    private String presentHis;
    @TableField("anamnesis")
    private String anamnesis;
    @TableField("sign")
    private String sign;
    @TableField("clinic_Dia")
    private String clinicDia;
    @TableField("advice")
    private String advice;
    @TableField("kindQuantity")
    private BigDecimal kindQuantity;
}
