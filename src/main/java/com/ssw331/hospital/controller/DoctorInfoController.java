package com.ssw331.hospital.controller;

import com.ssw331.hospital.dto.ConsultationInfo;
import com.ssw331.hospital.dto.DoctorInfo;
import com.ssw331.hospital.dto.serialization.Result;
import com.ssw331.hospital.dto.serialization.ResultResponse;
import com.ssw331.hospital.service.ConsultationInfoService;
import com.ssw331.hospital.service.DoctorInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 24053
 */
@RestController
@RequestMapping(path = "/api/doctor_info")
public class DoctorInfoController {
    private final DoctorInfoService doctorInfoService;
    private final ConsultationInfoService consultationInfoService;

    @Autowired
    public DoctorInfoController(DoctorInfoService doctorInfoService, ConsultationInfoService consultationInfoService) {
        this.doctorInfoService = doctorInfoService;
        this.consultationInfoService = consultationInfoService;
    }

    @GetMapping
    public Result<Object> getAllDoctorInfo() {
        List<DoctorInfo> doctorInfos = doctorInfoService.findAll();
        return ResultResponse.success(doctorInfos);
    }

    @GetMapping("id")
    public Result<Object> getDoctorInfoById(String id) {
        DoctorInfo doctorInfo = doctorInfoService.findByDoctorId(id);

        if (doctorInfo == null) {
            return ResultResponse.failure("Not Found");
        }

        return ResultResponse.success(doctorInfo);
    }

    @GetMapping("name")
    public Result<Object> getDoctorInfoByName(String name) {
        List<DoctorInfo> doctorInfos = doctorInfoService.findByDoctorName(name);

        if (doctorInfos == null || doctorInfos.isEmpty()) {
            return ResultResponse.failure("no doctor with this name");
        }

        return ResultResponse.success(doctorInfos);
    }

    @GetMapping("dept")
    public Result<Object> getDoctorInfoByDept(String dept) {
        List<DoctorInfo> doctorInfos = doctorInfoService.findByDepartmentName(dept);

        if (doctorInfos == null || doctorInfos.isEmpty()) {
            return ResultResponse.failure("No Doctor In the Department");
        }

        return ResultResponse.success(doctorInfos);
    }

    @PostMapping("add")
    public Result<Object> addDoctorInfo(DoctorInfo doctorInfo) {
        int res = doctorInfoService.insert(doctorInfo);
        if (res > 0) {
            return ResultResponse.success(doctorInfo);
        }

        return ResultResponse.failure("Add Doctor Failed");
    }

    @PutMapping("update")
    public Result<Object> updateDoctorInfo(DoctorInfo doctorInfo) {
        DoctorInfo exitedDoctorInfo = doctorInfoService.findByDoctorId(doctorInfo.getDoctorId());
        if (exitedDoctorInfo == null) {
            return ResultResponse.failure("Not Found this doctor");
        }
        int res = doctorInfoService.update(doctorInfo);
        // 这里可能需要异常处理
        if (res > 0) {
            return ResultResponse.success("Update Success");
        }

        return ResultResponse.failure("Update Failed");
    }

    @GetMapping("fee")
    public Result<Object> getDoctorInfo(String doctorId) {
        DoctorInfo doctorInfo = doctorInfoService.findByDoctorId(doctorId);
        if (doctorInfo == null) {
            return ResultResponse.failure("Not Found this doctor");
        }

        int registrationFee = switch (doctorInfo.getTitle()) {
            case "主任医师" -> 9;
            case "副主任医师" -> 7;
            case "住院医师", "医师" -> 4;
            default -> 6;
        };

        List<ConsultationInfo> consultationInfos = consultationInfoService.findByDoctorId(doctorId);

        return ResultResponse.success(new Object[]{registrationFee, consultationInfos});
    }
}
