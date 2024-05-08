package com.ssw331.hospital.service.impl;

import com.ssw331.hospital.dto.Prescription;
import com.ssw331.hospital.mapper.PrescriptionMapper;
import com.ssw331.hospital.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 24053
 */
@Component
public class PrescriptionServiceImpl implements PrescriptionService {
    private final PrescriptionMapper prescriptionMapper;

    @Autowired
    public PrescriptionServiceImpl(PrescriptionMapper prescriptionMapper) {
        this.prescriptionMapper = prescriptionMapper;
    }

    @Override
    public Prescription findPrescriptionById(String id) {
        return prescriptionMapper.selectById(id);
    }
}
