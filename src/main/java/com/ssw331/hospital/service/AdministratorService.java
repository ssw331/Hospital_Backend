package com.ssw331.hospital.service;

import com.ssw331.hospital.dto.Administrator;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author WWWsy
 */
@Service
public interface AdministratorService {
    /**
     * 获取所有管理员
     *
     * @return 管理员列表
     */
    List<Administrator> getAllAdministrators();
    /**
     * 根据管理员ID查找管理员信息
     *
     * @param id 管理员ID
     * @return 一个管理员信息
     */
    Administrator getAdministratorById(String id);
    /**
     * 根据管理员姓名查找管理员信息
     *
     * @param name 管理员姓名
     * @return 管理员信息列表（可能重名）
     */
    List<Administrator> getAdministratorByName(String name);
    /**
     * 添加新的管理员
     *
     * @param administrator 管理员
     * @return 添加的管理员信息
     */
    Administrator addAdministrator(Administrator administrator);
    /**
     * 更新指定管理员信息
     *
     * @param administrator 更改后的管理员信息
     * @return 更改后的管理员信息
     */
    Administrator updateAdministrator(Administrator administrator);
}
