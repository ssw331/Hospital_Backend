package com.ssw331.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ssw331.hospital.dto.DoctorInfo;
import com.ssw331.hospital.mapper.DoctorInfoMapper;
import com.ssw331.hospital.service.DoctorInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 24053
 */
@Component
public class DoctorInfoServiceImpl implements DoctorInfoService {
    private final DoctorInfoMapper doctorInfoMapper;

    @Autowired
    public DoctorInfoServiceImpl(DoctorInfoMapper doctorInfoMapper) {
        this.doctorInfoMapper = doctorInfoMapper;
    }

    @Override
    public List<DoctorInfo> findByDoctorId(int doctorId) {
        return doctorInfoMapper.selectList(new QueryWrapper<DoctorInfo>().eq("DOCTOR_ID", doctorId));
    }

    @Override
    public List<DoctorInfo> findByDepartmentName(String departmentName) {
        return doctorInfoMapper.selectList(new QueryWrapper<DoctorInfo>().eq("SECONDARY_DEPARTMENT", departmentName));
    }
}
