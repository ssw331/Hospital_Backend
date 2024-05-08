package com.ssw331.hospital.controller;

import com.ssw331.hospital.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ssw331.hospital.dto.serialization.Result;
import com.ssw331.hospital.dto.serialization.ResultResponse;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author WWWsy
 */
@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/whether")
    public Result<Object> getTreatmentFeedback(@RequestParam String patientId) {
        List<String> records = commentService.getDiagnosedHistoriesWithFeedback(patientId);
        if (records.isEmpty()) {
            return ResultResponse.failure("Not Found");
        }
        return ResultResponse.success(records);
    }

    @GetMapping("/GetAllFeedbacks")
    public Result<Object> getAllFeedbacks() {
        return ResultResponse.success(commentService.getAllFeedbacks());
    }

    @DeleteMapping("/DeleteFeedback")
    public Result<Object> deleteFeedback(@RequestParam String diagnosedId) {
        return ResultResponse.success(commentService.deleteFeedback(diagnosedId));
    }

    @PostMapping
    public Result<Object> createFeedback(@RequestParam String diagnoseId, BigDecimal treatmentScore, String evaluation) {
        return ResultResponse.success(commentService.createFeedback(diagnoseId, treatmentScore, evaluation));
    }
}

