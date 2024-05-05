package com.ssw331.hospital.controller;

import com.ssw331.hospital.dto.Patient;
import com.ssw331.hospital.dto.serialization.Result;
import com.ssw331.hospital.dto.serialization.ResultResponse;
import com.ssw331.hospital.service.PersonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author YourName
 */
@RestController
@RequestMapping("/api/personinfo")
public class PersonInfoController {
    private final PersonInfoService personInfoService;

    @Autowired
    public PersonInfoController(PersonInfoService personInfoService) {
        this.personInfoService = personInfoService;
    }

    // 根据患者 ID 获取个人信息
    @GetMapping
    public Result<Object> getPatientRegistrations(@RequestParam String patientId) {
        List<Patient> patients = personInfoService.getPatientById(patientId);

        if (patients.isEmpty()) {
            return ResultResponse.failure();
        }

        return ResultResponse.success(patients);
    }

    // 更新患者信息
    @PutMapping("/update")
    public Result<Object> updatePatient(@RequestBody Patient patient) {
        if (!personInfoService.patientExists(patient.getPatientId())) {
            return ResultResponse.failure("Patient not found.");
        }

        boolean updated = personInfoService.updatePatient(patient);

        if (!updated) {
            return ResultResponse.failure("Failed to update patient.");
        }

        return ResultResponse.success("Patient updated successfully.");
    }
}
