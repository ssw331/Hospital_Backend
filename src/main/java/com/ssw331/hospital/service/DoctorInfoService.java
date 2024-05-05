package com.ssw331.hospital.service;

import com.ssw331.hospital.dto.DoctorInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 24053
 */
@Service
public interface DoctorInfoService {
    List<DoctorInfo> findByDoctorId(int doctorId);
    List<DoctorInfo> findByDepartmentName(String departmentName);
}
