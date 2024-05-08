package com.ssw331.hospital.service;

import com.ssw331.hospital.dto.DiagnosedHistory;
import org.springframework.stereotype.Service;

/**
 * @author 24053
 */
@Service
public interface DiagnosedHistoryService {
    DiagnosedHistory getDiagnosedHistory(String diagnosedHistoryId);
}
