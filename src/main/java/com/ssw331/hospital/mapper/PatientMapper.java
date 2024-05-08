package com.ssw331.hospital.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ssw331.hospital.dto.Patient;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 24053
 */
@Mapper
public interface PatientMapper extends BaseMapper<Patient> {
}
