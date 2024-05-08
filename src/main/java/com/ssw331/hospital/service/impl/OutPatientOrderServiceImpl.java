package com.ssw331.hospital.service.impl;

import com.ssw331.hospital.dto.OutPatientOrder;
import com.ssw331.hospital.mapper.OutPatientOrderMapper;
import com.ssw331.hospital.service.OutPatientOrderService;
import org.springframework.stereotype.Component;

/**
 * @author 24053
 */
@Component
public class OutPatientOrderServiceImpl implements OutPatientOrderService {
    private final OutPatientOrderMapper outPatientOrderMapper;

    public OutPatientOrderServiceImpl(OutPatientOrderMapper outPatientOrderMapper) {
        this.outPatientOrderMapper = outPatientOrderMapper;
    }

    @Override
    public OutPatientOrder findById(String id) {
        return outPatientOrderMapper.selectById(id);
    }

    @Override
    public int save(OutPatientOrder order) {
        return outPatientOrderMapper.insert(order);
    }
}
