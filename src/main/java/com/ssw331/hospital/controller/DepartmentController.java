package com.ssw331.hospital.controller;

import com.ssw331.hospital.dto.Department;
import com.ssw331.hospital.dto.serialization.Result;
import com.ssw331.hospital.dto.serialization.ResultResponse;
import com.ssw331.hospital.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 24053
 */
@RestController
@RequestMapping(path = "/api/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public Result<Object> getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        return ResultResponse.success(departments);
    }
}
