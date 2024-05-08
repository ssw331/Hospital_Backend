package com.ssw331.hospital.service;

import com.ssw331.hospital.dto.Patient;
import org.springframework.stereotype.Service;

/**
 * @author 24053
 */
@Service
public interface PatientService {
    Patient getPatientById(String id);
}
