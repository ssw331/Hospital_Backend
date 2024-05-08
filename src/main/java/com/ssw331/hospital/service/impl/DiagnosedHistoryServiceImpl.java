package com.ssw331.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ssw331.hospital.dto.DiagnosedHistory;
import com.ssw331.hospital.mapper.DiagnosedHistoryMapper;
import com.ssw331.hospital.service.DiagnosedHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 24053
 */
@Component
public class DiagnosedHistoryServiceImpl implements DiagnosedHistoryService {
    private final DiagnosedHistoryMapper diagnosedHistoryMapper;

    @Autowired
    public DiagnosedHistoryServiceImpl(DiagnosedHistoryMapper diagnosedHistoryMapper) {
        this.diagnosedHistoryMapper = diagnosedHistoryMapper;
    }


    @Override
    public DiagnosedHistory getDiagnosedHistory(String diagnosedHistoryId) {

        return diagnosedHistoryMapper.selectOne(new QueryWrapper<DiagnosedHistory>().eq("DIAGNOSED_ID", diagnosedHistoryId));
    }


}
