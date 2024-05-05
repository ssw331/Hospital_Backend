package com.ssw331.hospital.controller;

import com.ssw331.hospital.dto.Prescription;
import com.ssw331.hospital.dto.serialization.Result;
import com.ssw331.hospital.dto.serialization.ResultResponse;
import com.ssw331.hospital.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author YourName
 */
@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {
    private final PrescriptionService prescriptionService;

    @Autowired
    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    // 根据诊断 ID 获取处方信息
    @GetMapping("/get-prescription")
    public Result<Object> getPrescriptionById(@RequestParam String diagnoseId) {
        Prescription prescriptionDetails = prescriptionService.getPrescriptionByDiagnoseId(diagnoseId);

        if (prescriptionDetails == null) {
            return ResultResponse.failure();
        }

        return ResultResponse.success(prescriptionDetails);
    }

    // 获取所有处方信息
    @GetMapping("/get-all")
    public Result<Object> getAllPrescriptions() {
        List<Prescription> prescriptions = prescriptionService.getAllPrescriptions();
        return ResultResponse.success(prescriptions);
    }

    // 获取指定处方的详细信息
    @GetMapping("/get-detail")
    public Result<Object> getPrescriptionDetail(@RequestParam String prescriptionId) {
        List<Object> prescriptionMedicines = prescriptionService.getPrescriptionMedicinesById(prescriptionId);

        if (prescriptionMedicines.isEmpty()) {
            return ResultResponse.failure();
        }

        return ResultResponse.success(prescriptionMedicines);
    }

    // 更新处方支付状态
    @PutMapping("/update-paystate")
    public Result<Object> updatePayState(@RequestParam String prescriptionId) {
        boolean success = prescriptionService.updatePayState(prescriptionId);

        if (!success) {
            return ResultResponse.failure();
        }

        return ResultResponse.success("Payment status updated successfully.");
    }
}