package com.ssw331.hospital.controller;

import com.ssw331.hospital.dto.PatientDetails;
import com.ssw331.hospital.dto.TreatmentRecord;
import com.ssw331.hospital.dto.serialization.Result;
import com.ssw331.hospital.dto.serialization.ResultResponse;
import com.ssw331.hospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jie Chu
 */
@RestController
@RequestMapping("/api/patients")
public class PatientController {
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // 添加新的诊疗记录
    @PostMapping("/post-treatment")
    public Result<Object> postTreatmentRecord(@RequestBody TreatmentRecord treatmentRecord) {
        TreatmentRecord createdRecord = patientService.addTreatmentRecord(treatmentRecord);
        return ResultResponse.success(createdRecord);
    }

    // 获取所有诊疗记录
    @GetMapping("/get-all-treatment")
    public Result<Object> getAllTreatmentRecords() {
        List<TreatmentRecord> records = patientService.getAllTreatmentRecords();
        return ResultResponse.success(records);
    }

    // 获取指定患者的详细信息
    @GetMapping("/details/{patientId}")
    public Result<Object> getPatientDetails(@PathVariable String patientId) {
        List<PatientDetails> patientDetails = patientService.getPatientDetailsById(patientId);

        if (patientDetails.isEmpty()) {
            return ResultResponse.failure();
        }
        return ResultResponse.success(patientDetails);
    }
}
