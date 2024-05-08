package com.ssw331.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ssw331.hospital.dto.Department;
import com.ssw331.hospital.mapper.DepartmentMapper;
import com.ssw331.hospital.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 24053
 */
@Component
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentMapper departmentMapper;

    @Autowired
    public DepartmentServiceImpl(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentMapper.selectList(new QueryWrapper<>());
    }
}
