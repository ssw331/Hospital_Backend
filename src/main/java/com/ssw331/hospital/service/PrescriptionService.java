package com.ssw331.hospital.service;

import com.ssw331.hospital.dto.Prescription;
import org.springframework.stereotype.Service;

/**
 * @author 24053
 */
@Service
public interface PrescriptionService {
    Prescription findPrescriptionById(String id);
}
