package com.ssw331.hospital.service;

import com.ssw331.hospital.dto.TreatmentFeedback;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
/**
 * @author WWWsy
 */
@Service
public interface CommentService {
    List<String> getDiagnosedHistoriesWithFeedback(String patientId);
    List<TreatmentFeedback> getAllFeedbacks();
    String deleteFeedback(String diagnosedId);
    String createFeedback(String diagnoseId, BigDecimal treatmentScore, String evaluation);
}
