package com.ssw331.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ssw331.hospital.dto.MedicineStock;
import com.ssw331.hospital.mapper.MedicineStockMapper;
import com.ssw331.hospital.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StockServiceImpl implements StockService{
    @Autowired
    private MedicineStockMapper medicineStockMapper;
    @Override
    public List<MedicineStock> getMedicineStocks() {
        List<MedicineStock> stocks = medicineStockMapper.selectList(new QueryWrapper<MedicineStock>()
                .eq("clean_Date",null));
        return stocks;
    }
}
