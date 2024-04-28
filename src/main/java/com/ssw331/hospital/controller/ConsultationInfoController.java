package com.ssw331.hospital.controller;

import com.ssw331.hospital.dto.ConsultationInfo;
import com.ssw331.hospital.dto.serialization.Result;
import com.ssw331.hospital.dto.serialization.ResultResponse;
import com.ssw331.hospital.service.ConsultationInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 24053
 */
@RestController
@RequestMapping(path = "/api/consultation_info")
public class ConsultationInfoController {
    private final ConsultationInfoService consultationInfoService;

    @Autowired
    public ConsultationInfoController(ConsultationInfoService consultationInfoService) {
        this.consultationInfoService = consultationInfoService;
    }

    @GetMapping
    public Result<Object> getAllConsultationInfo () {
        List<ConsultationInfo> result = consultationInfoService.findAll();
        if (result.isEmpty()) {
            return ResultResponse.failure();
        }
        return ResultResponse.success(result);
    }
}
