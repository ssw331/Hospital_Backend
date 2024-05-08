package com.ssw331.hospital.service;

import com.ssw331.hospital.dto.DoctorInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 24053
 */
@Service
public interface DoctorInfoService {
    DoctorInfo findByDoctorId(String doctorId);
    List<DoctorInfo> findByDoctorName(String doctorName);
    List<DoctorInfo> findByDepartmentName(String departmentName);
    List<DoctorInfo> findAll();
    int insert(DoctorInfo doctorInfo);
    int update(DoctorInfo doctorInfo);

}
