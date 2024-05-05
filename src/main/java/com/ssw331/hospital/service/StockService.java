package com.ssw331.hospital.service;

import com.ssw331.hospital.dto.MedicineStock;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface StockService {
    List<MedicineStock> getMedicineStocks();
}
