package com.ssw331.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ssw331.hospital.dto.ConsultationInfo;
import com.ssw331.hospital.mapper.ConsultationInfoMapper;
import com.ssw331.hospital.service.ConsultationInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 24053
 */
@Component
public class ConsultationInfoServiceImpl implements ConsultationInfoService {
    private final ConsultationInfoMapper consultationInfoMapper;

    @Autowired
    public ConsultationInfoServiceImpl(ConsultationInfoMapper consultationInfoMapper) {
        this.consultationInfoMapper = consultationInfoMapper;
    }

    @Override
    public List<ConsultationInfo> findAll() {
        return consultationInfoMapper.selectList(new QueryWrapper<>());
    }
}
