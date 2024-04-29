package com.ssw331.hospital.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ssw331.hospital.dto.MedicineSell;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author WWWsy
 */
@Mapper
public interface MedicineSellMapper extends BaseMapper<MedicineSell> {
    /**
     *
     * @param medicineName 药品名称
     * @return 找到的一行结果
     */
    default MedicineSell findByMedicineName(String medicineName) {
        QueryWrapper<MedicineSell> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("medicine_name", medicineName);
        return selectOne(queryWrapper);
    }
}