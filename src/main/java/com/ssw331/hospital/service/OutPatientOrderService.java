package com.ssw331.hospital.service;

import com.ssw331.hospital.dto.OutPatientOrder;
import org.springframework.stereotype.Service;

/**
 * @author 24053
 */
@Service
public interface OutPatientOrderService {
    OutPatientOrder findById(String id);
    int save(OutPatientOrder order);
}
