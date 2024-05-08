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
@Getter
@Data
@Table("patient")
public class Patient {
    @TableId("PATIENT_ID")
    private String patientId;
    @TableField("NAME")
    private String name;
    @TableField("GENDER")
    private BigDecimal gender;
    @TableField("BIRTH_DATE")
    private Timestamp birthDate;
    @TableField("CONTACT")
    private String contact;
    @TableField("COLLEGE")
    private String college;
    @TableField("COUNSELLOR")
    private String counsellor;

    // ?
    @TableField("PASSWORD")
    private String password;
}
