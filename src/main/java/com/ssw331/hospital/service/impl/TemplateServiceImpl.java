package com.ssw331.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ssw331.hospital.dto.Template;
import com.ssw331.hospital.mapper.TemplateMapper;
import com.ssw331.hospital.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TemplateServiceImpl implements TemplateService{
    @Autowired
    private TemplateMapper templateMapper;

    @Override
    public List<Template> getAllTemplates()
    {
        return templateMapper.selectList(new QueryWrapper<>());
    }
}
