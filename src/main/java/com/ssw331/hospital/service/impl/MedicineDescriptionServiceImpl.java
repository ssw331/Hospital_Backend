package com.ssw331.hospital.service.impl;

import com.ssw331.hospital.dto.MedicineDescription;
import com.ssw331.hospital.mapper.MedicineDescriptionMapper;
import com.ssw331.hospital.service.MedicineDescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 24053
 */
@Component
public class MedicineDescriptionServiceImpl implements MedicineDescriptionService {
    private final MedicineDescriptionMapper medicineDescriptionMapper;

    @Autowired
    public MedicineDescriptionServiceImpl(MedicineDescriptionMapper medicineDescriptionMapper) {
        this.medicineDescriptionMapper = medicineDescriptionMapper;
    }


    @Override
    public MedicineDescription findMedicineDescriptionByName(String name) {
        return medicineDescriptionMapper.selectById(name);
    }
}
