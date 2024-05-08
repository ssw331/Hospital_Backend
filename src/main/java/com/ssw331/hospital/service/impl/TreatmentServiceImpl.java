package com.ssw331.hospital.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ssw331.hospital.dto.*;
import com.ssw331.hospital.mapper.*;
import com.ssw331.hospital.service.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author WWWsy
 */
@Service
public class TreatmentServiceImpl implements TreatmentService {
    @Autowired
    private TreatmentRecordMapper treatmentRecordMapper;
    @Autowired
    private RegistrationMapper registrationMapper;
    @Autowired
    private PrescriptionMapper prescriptionMapper;
    @Autowired
    private PrescriptionMedicineMapper prescriptionMedicineMapper;
    @Autowired
    private MedicineSellMapper medicineSellMapper;
    @Autowired
    private DiagnosedHistoryMapper diagnosedHistoryMapper;

    @Override
    @Transactional
    public String createTreatmentRecord(TreatmentRecordInputModel inputModel) {
        // 根据输入生成诊断ID和处方ID
        LocalDateTime now = LocalDateTime.now();
        String diagnoseId = now.format(DateTimeFormatter.ofPattern("yyyyMMdd")) + inputModel.getPatientId() + inputModel.getDoctorId() + inputModel.getPeriod();
        String prescriptionId = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + "000" + inputModel.getPatientId() + inputModel.getDoctorId() + inputModel.getPeriod();
        Date today = java.sql.Date.valueOf(now.toLocalDate());

        // 更新挂号表中的内容
        Registration registration = new Registration();
        registration.setPatientId(inputModel.getPatientId());
        registration.setDoctorId(inputModel.getDoctorId());
        registration.setAppointmentTime(today);
        registration.setPeriod(new BigDecimal(inputModel.getPeriod()));
        registration.setPrescriptionId(prescriptionId);


        // 在就诊记录表中插入一行
        TreatmentRecord treatmentRecord = new TreatmentRecord();
        treatmentRecord.setDiagnosedId(diagnoseId);
        treatmentRecord.setPatientId(inputModel.getPatientId());
        treatmentRecord.setDoctorId(inputModel.getDoctorId());
        treatmentRecordMapper.insert(treatmentRecord);

        // 就诊信息表中插入一行
        DiagnosedHistory diagnosedHistory = new DiagnosedHistory();
        diagnosedHistory.setDiagnoseId(diagnoseId);
        diagnosedHistory.setDiagnoseTime(Timestamp.valueOf(now));
        diagnosedHistory.setCommentState(BigDecimal.ZERO);
        diagnosedHistory.setSelfReported(inputModel.getSelfReported());
        diagnosedHistory.setPresentHis(inputModel.getPresentHis());
        diagnosedHistory.setAnamnesis(inputModel.getAnamnesis());
        diagnosedHistory.setSign(inputModel.getSign());
        diagnosedHistory.setClinicDia(inputModel.getClinicDia());
        diagnosedHistory.setAdvice(inputModel.getAdvice());
        diagnosedHistory.setKindQuantity(BigDecimal.ZERO);

        // 处方表中插入一行
        Prescription prescription = new Prescription();
        prescription.setPrescriptionId(prescriptionId);
        prescription.setDoctorId(inputModel.getDoctorId());
        prescription.setTotalPrice(BigDecimal.ZERO);
        prescription.setPayState(BigDecimal.ZERO);
        prescriptionMapper.insert(prescription);

        // 处理具体的药品信息，不同的药品之间用;分开，
        String[] medicines = inputModel.getMedicine().split(";");
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (String medicine : medicines) {
            //+分割药品和注意事项
            String[] medicineInfo = medicine.split("\\+");
            if (medicineInfo.length != 2) {
                continue;
            }
            //分割药品名称和数量
            String[] medicineNameAndQuantity = medicineInfo[0].split("\\*");
            if (medicineNameAndQuantity.length != 2) {
                continue;
            }
            String medicineName = medicineNameAndQuantity[0];
            BigDecimal quantity;
            try {
                quantity = new BigDecimal(medicineNameAndQuantity[1]);
            } catch (NumberFormatException e) {
                continue;
            }
            String medicationInstruction = medicineInfo[1];
            // 查到药品价格
            MedicineSell medicineSell = medicineSellMapper.findByMedicineName(medicineName);
            if (medicineSell == null) {
                continue;
            }
            // 在处方药品表中插入一行
            PrescriptionMedicine prescriptionMedicine = new PrescriptionMedicine();
            prescriptionMedicine.setPrescriptionId(prescriptionId);
            prescriptionMedicine.setMedicineName(medicineName);
            prescriptionMedicine.setMedicationInstruction(medicationInstruction);
            prescriptionMedicine.setMedicinePrice(medicineSell.getSellingPrice());
            prescriptionMedicine.setQuantity(quantity);

            // 更新总价
            totalPrice = totalPrice.add(medicineSell.getSellingPrice().multiply(quantity));
            diagnosedHistory.setKindQuantity(diagnosedHistory.getKindQuantity().add(BigDecimal.ONE));
            prescriptionMedicineMapper.insert(prescriptionMedicine);
        }
        prescription.setTotalPrice(totalPrice);
        diagnosedHistoryMapper.insert(diagnosedHistory);
        prescription.setTotalPrice(totalPrice);
        prescriptionMapper.updateById(prescription);
        registrationMapper.updateRegistration(registration);
        return "Treatment record created successfully.";
    }

    @Override
    public TreatmentRecord getTreatmentRecord(String diagnosedId) {
        QueryWrapper<TreatmentRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("DIAGNOSED_ID", diagnosedId);
        return treatmentRecordMapper.selectOne(queryWrapper);
    }

    @Override
    public List<TreatmentRecord> getTreatmentRecordsByPatientId(String patientId) {
        QueryWrapper<TreatmentRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("PATIENT_ID", patientId);

        return treatmentRecordMapper.selectList(queryWrapper);
    }
}

