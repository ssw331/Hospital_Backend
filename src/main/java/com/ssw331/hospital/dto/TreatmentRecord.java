package com.ssw331.hospital.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;

/**
 * @author WWWsy
 */
@Data
@Getter
@TableName("treatment_record")
public class TreatmentRecord {
    @TableId("diagnosed_Id")
    private String diagnosedId;
    @TableField("patient_id")
    private String patientId;
    @TableField("doctor_id")
    private String doctorId;
    @TableField("leave_note_id")
    private String leavenoteId;
}
