package com.ssw331.hospital.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author WWWsy
 */
@Getter
@Data
@TableName("chat_record")
public class ChatRecord {
    @TableField("recordid")
    private String recordId;
    @TableField("doctor_id")
    private String doctorId;
    @TableField("patient_id")
    private String patientId;
    @TableField("message")
    private String message;
    @TableField("sender_type")
    private BigDecimal senderType;
    @TableId("timestamp")
    private Timestamp timeStamp;
    @TableField("read_status")
    private BigDecimal readStatus;
}
