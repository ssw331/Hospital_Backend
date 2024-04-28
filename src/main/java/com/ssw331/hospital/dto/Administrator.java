package com.ssw331.hospital.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

/**
 * @author WWWsy
 */
@Getter
@Data
@TableName("administrator")
public class Administrator {
    @TableId("administrator_id")
    private String administratorId;
    @TableField("name")
    private String name;
    @TableField("gender")
    private boolean gender;
    @TableField("birthdate")
    private LocalDate birthdate;
    @TableField("contact")
    private String contact;
    @TableField("password")
    private String password;
}