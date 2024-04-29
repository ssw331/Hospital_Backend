package com.ssw331.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ssw331.hospital.dto.DiagnosedHistory;
import com.ssw331.hospital.dto.TreatmentFeedback;
import com.ssw331.hospital.dto.TreatmentRecord;
import com.ssw331.hospital.mapper.DiagnosedHistoryMapper;
import com.ssw331.hospital.mapper.TreatmentFeedbackMapper;
import com.ssw331.hospital.mapper.TreatmentRecordMapper;
import com.ssw331.hospital.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author WWWsy
 */
@Component
public class CommentServiceImpl implements CommentService {
    @Autowired
    private DiagnosedHistoryMapper diagnosedHistoryMapper;
    @Autowired
    private TreatmentRecordMapper treatmentRecordMapper;
    @Autowired
    private TreatmentFeedbackMapper treatmentFeedbackMapper;

    @Override
    public List<String> getDiagnosedHistoriesWithFeedback(String patientId) {
        //现根据病人ID从诊断记录表中找到对应的诊断记录号
        List<String> diagnosedIds = treatmentRecordMapper.selectList(new QueryWrapper<TreatmentRecord>()
                        .eq("patient_id", patientId))
                .stream()
                .map(TreatmentRecord::getDiagnosedId)
                .collect(Collectors.toList());

        //再在诊断信息表中找对应已经评价过的诊断记录，返回诊断记录号
        if (!diagnosedIds.isEmpty()) {
            return diagnosedHistoryMapper.selectList(new QueryWrapper<DiagnosedHistory>()
                            .in("diagnose_id", diagnosedIds)
                            .eq("commentstate", BigDecimal.ONE))
                    .stream()
                    .map(DiagnosedHistory::getDiagnoseId)
                    .collect(Collectors.toList());
        }

        return List.of();
    }

    @Override
    public List<TreatmentFeedback> getAllFeedbacks() {
        return  treatmentFeedbackMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public String deleteFeedback(String diagnosedId) {
        boolean feedbackExists = treatmentFeedbackMapper.deleteById(diagnosedId) > 0;

        // 更新就诊信息表中评价状态为0，表示未评价
        DiagnosedHistory record = diagnosedHistoryMapper.selectById(diagnosedId);
        if (record != null && feedbackExists) {
            record.setCommentState(BigDecimal.ZERO);
            diagnosedHistoryMapper.updateById(record);
            return "Feedback and corresponding record updated successfully.";
        } else if (!feedbackExists) {
            return "No feedback record found with ID: " + diagnosedId;
        } else {
            return "No diagnosed history record found with ID: " + diagnosedId;
        }
    }

    @Override
    public String createFeedback(String diagnoseId, BigDecimal treatmentScore, String evaluation) {
        TreatmentRecord treatmentRecord = treatmentRecordMapper.selectById(diagnoseId);
        if (treatmentRecord == null) {
            return "No corresponding treatment record found.";
        }

        DiagnosedHistory record = diagnosedHistoryMapper.selectById(diagnoseId);
        if (record != null && record.getCommentState().equals(BigDecimal.ZERO)) {
            //更新就诊信息表的评价状态
            record.setCommentState(BigDecimal.ONE);
            diagnosedHistoryMapper.updateById(record);

            //插入一条反馈信息
            TreatmentFeedback feedback = new TreatmentFeedback();
            feedback.setDiagnosedId(diagnoseId);
            feedback.setPatientId(treatmentRecord.getPatientId());
            feedback.setDoctorId(treatmentRecord.getDoctorId());
            feedback.setTreatmentScore(treatmentScore);
            feedback.setEvaluation(evaluation);

            treatmentFeedbackMapper.insert(feedback);
            return "Feedback created successfully.";
        }
        return "Unable to create feedback (no such record or already reviewed)";
    }
    }

