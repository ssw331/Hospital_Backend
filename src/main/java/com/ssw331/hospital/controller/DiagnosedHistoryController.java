package com.ssw331.hospital.controller;

import com.ssw331.hospital.dto.DiagnosedHistory;
import com.ssw331.hospital.dto.OutPatientOrder;
import com.ssw331.hospital.dto.Prescription;
import com.ssw331.hospital.dto.TreatmentRecord;
import com.ssw331.hospital.dto.serialization.Result;
import com.ssw331.hospital.dto.serialization.ResultResponse;
import com.ssw331.hospital.service.DiagnosedHistoryService;
import com.ssw331.hospital.service.OutPatientOrderService;
import com.ssw331.hospital.service.PrescriptionService;
import com.ssw331.hospital.service.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

/**
 * @author 24053
 */
@RestController
@RequestMapping(path = "/api/diagnosed_history")
public class DiagnosedHistoryController {
    private final DiagnosedHistoryService diagnosedHistoryService;
    private final TreatmentService treatmentService;
    private final PrescriptionService prescriptionService;
    private final OutPatientOrderService outPatientOrderService;

    @Autowired
    public DiagnosedHistoryController(DiagnosedHistoryService diagnosedHistoryService, TreatmentService treatmentService, PrescriptionService prescriptionService, OutPatientOrderService outPatientOrderService) {
        this.diagnosedHistoryService = diagnosedHistoryService;
        this.treatmentService = treatmentService;
        this.prescriptionService = prescriptionService;
        this.outPatientOrderService = outPatientOrderService;
    }

    @GetMapping("getPatientRecords")
    public Result<Object> getPatientRecords(String patientId) {
        List<TreatmentRecord> treatmentRecords = treatmentService.getTreatmentRecordsByPatientId(patientId);

        if (treatmentRecords.isEmpty()) {
            return ResultResponse.failure();
        }

        List<DiagnosedHistory> diagnosedHistories = new ArrayList<>();

        for (TreatmentRecord treatmentRecord : treatmentRecords) {
            DiagnosedHistory diagnosedHistory = diagnosedHistoryService.getDiagnosedHistory(treatmentRecord.getDiagnosedId());
            if (diagnosedHistory != null) {
                diagnosedHistories.add(diagnosedHistory);
            }

        }

        return ResultResponse.success(diagnosedHistories);
    }

    @PostMapping("alipayNotify")
    public Result<Object> notifyUrl(@RequestParam Map<String, String> parameters) {
        if (parameters.containsKey("out_trade_no")) {
            String prescriptionId = parameters.get("out_trade_no");
            StringBuilder tmpPrescriptionId = new StringBuilder(prescriptionId);
            String diagnosedHistoryId = tmpPrescriptionId.delete(8, 11).toString();

            TreatmentRecord treatmentRecord = treatmentService.getTreatmentRecord(diagnosedHistoryId);
            Prescription prescription = prescriptionService.findPrescriptionById(prescriptionId);
            prescription.setPayState(BigDecimal.valueOf(1));
            OutPatientOrder order = outPatientOrderService.findById(prescriptionId);

            if (order == null) {
                OutPatientOrder newOrder = new OutPatientOrder();
                newOrder.setPatientId(prescriptionId);
                newOrder.setPatientId(treatmentRecord.getPatientId());
                newOrder.setOrderTime(Timestamp.valueOf(String.valueOf(new Date())));

                int res = outPatientOrderService.save(newOrder);

                if (res < 1) {
                    return ResultResponse.failure();
                }
            }
        }

        return ResultResponse.success("SUCCESS");
    }
}
