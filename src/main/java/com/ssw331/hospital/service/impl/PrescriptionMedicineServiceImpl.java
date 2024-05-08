package com.ssw331.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ssw331.hospital.dto.PrescriptionMedicine;
import com.ssw331.hospital.mapper.PrescriptionMedicineMapper;
import com.ssw331.hospital.service.PrescriptionMedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 24053
 */
@Component
public class PrescriptionMedicineServiceImpl implements PrescriptionMedicineService {
    private final PrescriptionMedicineMapper prescriptionMedicineMapper;

    @Autowired
    public PrescriptionMedicineServiceImpl(PrescriptionMedicineMapper prescriptionMedicineMapper) {
        this.prescriptionMedicineMapper = prescriptionMedicineMapper;
    }

    @Override
    public List<PrescriptionMedicine> getPrescriptionMedicineByPrescriptionId(String prescriptionId) {
        return prescriptionMedicineMapper.selectList(new QueryWrapper<PrescriptionMedicine>().eq("PRESCRIPTION_ID", prescriptionId));
    }
}
