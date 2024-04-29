package com.ssw331.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ssw331.hospital.dto.Administrator;
import com.ssw331.hospital.mapper.AdministratorMapper;
import com.ssw331.hospital.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;

/**
 * @author WWWsy
 */
@Component
public class AdministratorServiceImpl implements AdministratorService {
    @Autowired
    private AdministratorMapper administratorMapper;

    @Override
    public List<Administrator> getAllAdministrators() {
        return administratorMapper.selectList(null);
    }

    @Override
    public Administrator getAdministratorById(String id) {
        return administratorMapper.selectById(id);
    }

    @Override
    public List<Administrator> getAdministratorByName(String name) {
        return administratorMapper.selectList(new QueryWrapper<Administrator>().eq("name", name));
    }

    @Override
    public Administrator addAdministrator(Administrator administrator) {
        administratorMapper.insert(administrator);
        return administrator;
    }

    @Override
    public Administrator updateAdministrator(Administrator administrator) {
        administratorMapper.updateById(administrator);
        return administrator;
    }
}
