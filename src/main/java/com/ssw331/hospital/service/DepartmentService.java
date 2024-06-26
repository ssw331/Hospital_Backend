package com.ssw331.hospital.service;

import com.ssw331.hospital.dto.Department;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 24053
 */
@Service
public interface DepartmentService {
    List<Department> getAllDepartments();
}
