package com.ssw331.hospital.controller;

import com.ssw331.hospital.dto.TreatmentRecordInputModel;
import com.ssw331.hospital.dto.serialization.Result;
import com.ssw331.hospital.dto.serialization.ResultResponse;
import com.ssw331.hospital.service.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WWWsy
 */
@RestController
@RequestMapping("/api/confirm")
public class TreatmentController {
    @Autowired
    private TreatmentService treatmentService;

    @PostMapping
    public Result<Object> postTreatmentRecord2(@RequestBody TreatmentRecordInputModel inputModel) {
        try {
            String result = treatmentService.createTreatmentRecord(inputModel);
            return ResultResponse.success(result);
        } catch (Exception ex) {
            return ResultResponse.failure("Posting treatment record failed");
        }
    }
}
