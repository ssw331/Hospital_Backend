package com.ssw331.hospital.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author 24053
 */
@Getter
@Data
@Table("medicine_description")
public class MedicineDescription {
    @TableId("MEDICINE_NAME")
    private String medicineName;
    @TableField("MEDICINE_TYPE")
    private String medicineType;
    @TableField("APPLICABLE_SYMPTOM")
    private String applicableSymptom;
    @TableField("VULGO")
    private String vulGo;
    @TableField("SPECIFICATION")
    private String specification;
    @TableField("ADMINISTRATION")
    private String administration;
    @TableField("SINGLEDOSE")
    private String singleDose;
    @TableField("ATTENTION")
    private String attention;
    @TableField("FREQUENCY")
    private String frequency;
}
