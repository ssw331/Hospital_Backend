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
@TableName("treatment_feedback")
public class TreatmentFeedback {
    @TableId("diagnosedId")
    private String diagnosedId;
    @TableField("patient_id")
    private String patientId;
    @TableField("doctor_id")
    private String doctorId;
    @TableField("treatment_score")
    private BigDecimal treatmentScore;
    @TableField("evaluation")
    private String evaluation;
}
