package com.ssw331.hospital.service;

import com.ssw331.hospital.dto.Template;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface TemplateService {
    List<Template> getAllTemplates();
}
