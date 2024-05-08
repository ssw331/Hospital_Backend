package com.ssw331.hospital.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;

/**
 * @author YiR2002
 */
@Data
@Getter
@TableName("template")
public class Template
{
    @TableId("name")
    private String name;
    @TableField("problem")
    private String problem;
    @TableField("illness")
    private String illness;
    @TableField("column1")
    private String column1;
    @TableField("symptom")
    private String symptom;
    @TableField("diagnose")
    private String diagnose;
    @TableField("prescription")
    private String prescription;
    @TableField("medicine")
    private String medicine;
}
