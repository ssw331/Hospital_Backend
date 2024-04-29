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
@Table("doctor")
public class DoctorInfo {
    @TableId
    private String doctorId;
    @TableField
    private String name;
    @TableField
    private BigDecimal gender;
    @TableField
    private Timestamp birthDate;
    @TableField
    private String title;
    @TableField
    private String contact;
    @TableField
    private String secondaryDepartment;
    @TableField
    private String photoUrl;

    // ?
    @TableField
    private String password;
    @TableField
    private String skillIn;
}
