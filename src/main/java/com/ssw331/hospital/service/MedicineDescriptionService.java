package com.ssw331.hospital.service;

import com.ssw331.hospital.dto.MedicineDescription;
import org.springframework.stereotype.Service;

/**
 * @author 24053
 */
@Service
public interface MedicineDescriptionService {
    MedicineDescription findMedicineDescriptionByName(String name);
}
