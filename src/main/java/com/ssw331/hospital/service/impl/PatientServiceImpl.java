package com.ssw331.hospital.service.impl;

import com.ssw331.hospital.dto.Patient;
import com.ssw331.hospital.mapper.PatientMapper;
import com.ssw331.hospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 24053
 */
@Component
public class PatientServiceImpl implements PatientService {
    private final PatientMapper patientMapper;

    @Autowired
    public PatientServiceImpl(PatientMapper patientMapper) {
        this.patientMapper = patientMapper;
    }

    @Override
    public Patient getPatientById(String id) {
        return patientMapper.selectById(id);
    }
}
