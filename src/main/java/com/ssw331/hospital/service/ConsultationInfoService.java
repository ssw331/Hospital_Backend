package com.ssw331.hospital.service;

import com.ssw331.hospital.dto.ConsultationInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 24053
 */
@Service
public interface ConsultationInfoService {
    List<ConsultationInfo> findAll();
    List<ConsultationInfo> findByDoctorId(String doctorId);
    ConsultationInfo findByAll(ConsultationInfo consultationInfo);
    int save(ConsultationInfo consultationInfo);
    int delete(ConsultationInfo consultationInfo);
}
