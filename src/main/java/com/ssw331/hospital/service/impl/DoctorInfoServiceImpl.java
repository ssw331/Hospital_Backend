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
    public DoctorInfo findByDoctorId(String doctorId) {
        return doctorInfoMapper.selectById(doctorId);
    }

    @Override
    public List<DoctorInfo> findByDoctorName(String doctorName) {
        return doctorInfoMapper.selectList(new QueryWrapper<DoctorInfo>().eq("NAME", doctorName));
    }

    @Override
    public List<DoctorInfo> findByDepartmentName(String departmentName) {
        return doctorInfoMapper.selectList(new QueryWrapper<DoctorInfo>().eq("SECONDARY_DEPARTMENT", departmentName));
    }

    @Override
    public List<DoctorInfo> findAll() {
        return doctorInfoMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public int insert(DoctorInfo doctorInfo) {
        return doctorInfoMapper.insert(doctorInfo);
    }

    @Override
    public int update(DoctorInfo doctorInfo) {
        return doctorInfoMapper.updateById(doctorInfo);
    }
}
