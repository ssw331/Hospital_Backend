package com.ssw331.hospital.service;

import com.ssw331.hospital.dto.TreatmentRecord;
import com.ssw331.hospital.dto.TreatmentRecordInputModel;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author WWWsy
 */
@Service
public interface TreatmentService {
    /**
     *
     * @param inputModel 输入的就诊记录类型
     * @return ?
     */
    String createTreatmentRecord(TreatmentRecordInputModel inputModel);

    TreatmentRecord getTreatmentRecord(String diagnosedId);

    List<TreatmentRecord> getTreatmentRecordsByPatientId(String patientId);
}
