package com.ssw331.hospital.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author 24053
 */
@Data
@Getter
@Table("department")
public class Department {
    @TableId("DEPARTMENT_NAME")
    private String departmentName;
    @TableField("DEPARTMENT_DESCRIPTION")
    private String departmentDescription;
}
