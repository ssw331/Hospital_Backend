package com.ssw331.hospital.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.ssw331.hospital.dto.*;
import com.ssw331.hospital.dto.serialization.Result;
import com.ssw331.hospital.dto.serialization.ResultResponse;
import com.ssw331.hospital.service.*;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

/**
 * @author 24053
 */
@RestController
@RequestMapping(path = "/api/diagnosed_history")
public class DiagnosedHistoryController {

    private static final Logger log = LoggerFactory.getLogger(DiagnosedHistoryController.class);

    @Getter
    @Setter
    private static class Record {
        TreatmentRecord treatmentRecord;
        Prescription prescription;
        List<MedicineDescription> medicineDescriptions;
        DoctorInfo doctorInfo;
    }

    private final DiagnosedHistoryService diagnosedHistoryService;
    private final TreatmentService treatmentService;
    private final PrescriptionService prescriptionService;
    private final OutPatientOrderService outPatientOrderService;
    private final PatientService patientService;
    private final PrescriptionMedicineService prescriptionMedicineService;
    private final MedicineDescriptionService medicineDescriptionService;
    private final DoctorInfoService doctorInfoService;

    @Autowired
    public DiagnosedHistoryController(DiagnosedHistoryService diagnosedHistoryService, TreatmentService treatmentService, PrescriptionService prescriptionService, OutPatientOrderService outPatientOrderService, PatientService patientService, PrescriptionMedicineService prescriptionMedicineService, MedicineDescriptionService medicineDescriptionService, DoctorInfoService doctorInfoService) {
        this.diagnosedHistoryService = diagnosedHistoryService;
        this.treatmentService = treatmentService;
        this.prescriptionService = prescriptionService;
        this.outPatientOrderService = outPatientOrderService;
        this.patientService = patientService;
        this.prescriptionMedicineService = prescriptionMedicineService;
        this.medicineDescriptionService = medicineDescriptionService;
        this.doctorInfoService = doctorInfoService;
    }

    @GetMapping("getPatientRecords")
    public Result<Object> getPatientRecords(String patientId) {
        List<TreatmentRecord> treatmentRecords = treatmentService.getTreatmentRecordsByPatientId(patientId);

        if (treatmentRecords.isEmpty()) {
            return ResultResponse.failure("Not Found Treatment Record");
        }

        List<DiagnosedHistory> diagnosedHistories = new ArrayList<>();

        for (TreatmentRecord treatmentRecord : treatmentRecords) {
            DiagnosedHistory diagnosedHistory = diagnosedHistoryService.getDiagnosedHistory(treatmentRecord.getDiagnosedId());
            if (diagnosedHistory != null) {
                diagnosedHistories.add(diagnosedHistory);
            }

        }

        return ResultResponse.success(diagnosedHistories);
    }

    @PostMapping("alipayNotify")
    public Result<Object> notifyUrl(@RequestParam Map<String, String> parameters) {
        if (parameters.containsKey("out_trade_no")) {
            String prescriptionId = parameters.get("out_trade_no");
            StringBuilder tmpPrescriptionId = new StringBuilder(prescriptionId);
            String diagnosedHistoryId = tmpPrescriptionId.delete(8, 11).toString();

            TreatmentRecord treatmentRecord = treatmentService.getTreatmentRecord(diagnosedHistoryId);
            Prescription prescription = prescriptionService.findPrescriptionById(prescriptionId);
            prescription.setPayState(BigDecimal.valueOf(1));
            OutPatientOrder order = outPatientOrderService.findById(prescriptionId);

            if (order == null) {
                OutPatientOrder newOrder = new OutPatientOrder();
                newOrder.setPatientId(prescriptionId);
                newOrder.setPatientId(treatmentRecord.getPatientId());
                newOrder.setOrderTime(Timestamp.valueOf(String.valueOf(new Date())));

                int res = outPatientOrderService.save(newOrder);

                if (res < 1) {
                    return ResultResponse.failure("Save Failed");
                }
            }
        }

        return ResultResponse.success("SUCCESS");
    }

    // TODO: other apis
    @GetMapping("alipayReturn")
    public Result<Object> returnUrl(@RequestParam Map<String, String> parameters) {
        if (parameters.containsKey("out_trade_no")) {
            String prescriptionId = parameters.get("out_trade_no");
            Prescription prescription = prescriptionService.findPrescriptionById(prescriptionId);
            if (prescription == null) {
                return ResultResponse.failure("Not Found Prescription");
            }

            //  """ 使用文本块
            String htmlContent = """  
                    <meta charset="UTF-8">
                                    <html>
                                    <head>
                                        <title>Payment Complete</title>
                                    </head>
                                    <body>
                                        <h1>已付款，窗口将自动关闭</h1>
                                        <p>订单号: " + %s + @"</p>
                                        <script>
                                            // 使用JavaScript在页面加载后自动关闭窗口
                                            window.onload = function() {
                                                window.setTimeout(function() {
                                                    window.close();
                                                }, 2000); // 2000毫秒（5秒）后关闭窗口，您可以根据需要更改此时间
                                            };
                                        </script>
                                    </body>
                                    </html>
                    """.formatted(prescriptionId);

            // contentType: text/html
            return ResultResponse.success(htmlContent);
        } else {
            String htmlContent = """
                    <meta charset="UTF-8">
                                    <html>
                                    <head>
                                        <title>Payment Complete</title>
                                    </head>
                                    <body>
                                        <h1>查询失败</h1>
                                        <script>
                                            // 使用JavaScript在页面加载后自动关闭窗口
                                            window.onload = function() {
                                                window.setTimeout(function() {
                                                    window.close();
                                                }, 2000); // 2000毫秒（5秒）后关闭窗口，您可以根据需要更改此时间
                                            };
                                        </script>
                                    </body>
                                    </html>"
                    """;

            // contentType: text/html
            return ResultResponse.success(htmlContent);
        }
    }

    @GetMapping("payBill")
    public Result<Object> payBill(String diagnosedHistoryId) {
        StringBuilder tmpDiagnosedHistoryId = new StringBuilder(diagnosedHistoryId);
        String prescriptionId = String.valueOf(tmpDiagnosedHistoryId.insert(8, "000"));

        Prescription prescription = prescriptionService.findPrescriptionById(prescriptionId);
        if (prescription == null) {
            return ResultResponse.failure("Not Found Prescription");
        }

        if (prescription.getPayState().equals(BigDecimal.valueOf(0))) {
            return ResultResponse.failure("订单已支付");
        }

        BigDecimal totalPrice = prescription.getTotalPrice();

        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", "2021004143682529", "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCqzQjHyBX/UAT5Fu+FFEBO+unfEWTHYFbvqDUMVvoVSFmbbrCs806vGj68K+zsQ2OPbpThVbunR8/e8PPNT4TmrnXlFI7S5cqTOaqzJTuwHAENmfCY8w0uvdkMyil2SK+4dtEqVhCK3vb+W71CxW/GJfSjPkTdVhlvBhX9NRYn/kw5wJLBV94ED+dG4lFcmcDDsePFbHHLf+okR2/F/0CRJveuw1Hb0WcCx6uxoN1snKcrCmGnWlXBCePHOASYEVn0owwc7IyZEoAN0v7sqABPH9EYYGlr9BgWN10NU2aKe83SvzsD7cnDd2jejp95NjIDzKUdmXPtUKuhwDGqExHXAgMBAAECggEBAIaukwPmVOI0WReJ7GlF5Mfit1Q8xPkqkKL+QoZud1kc6fvUtuBKI3uT+HAtOu+62+EYU7G0BvsLd3DXE4iL8cJtQ23WXiETLkAVsvL8oyplircM8lnJHlYDQgoBx9wLyPRuTNVTdEvtMVq1QHJMea0H9PZcfbUC1Z9kbyZxG/2AENNh5h9TbGBl7mp0ObV/IEp7ODupfupcxWhI+yQ+zIloip0wWTpsiMcY9rMPJZSyU8zpqwo0K8aFe1ktftgBfnApM95sQ2YzcmrcMiqAmS5KwzcMoQXKkd8qRJhv3giU7ETzZ1z4FMtJ1OpLhb0UvYDkDx5gbqKtfr2Ll9py1OECgYEA2btTpj631XRhQ1mcUcLVEhYAyN7BNQcfQCVSeZIeWnM90+KLbDL4aH7r3wneJH3yeRT+oIXDjLZcYGpr3FNl3X1d0F5EYGSR4ZZcTptHUIlIAbFRz6+8jEt3Yxus/Ux2FdfCOsGYKCTZ2WrOrqRKtaakoAVxUtJGAqZ4Y173as8CgYEAyNIXPH9UA79BqeYvtzU5CYClEua9VeSq7oz1jxvnE8oWfwwTzVAgik1HiN45EwMYMMPQ9ql+gIuJ2MWVwU1RRCnLqILdTrh0zmNCWisuMxUc/J8Qnw2wOyVuvvOAHcPCTcXJXxeG5IIeTq+ohO0U9zLuxjgcVZmZdbcJNAsSinkCgYEAjsSbhA3qAwgt4ri2nJIRnqtY1l3h+IB0CF6wptCeIJciOnZ5D3iYlARta7GPvQPs42JV2DH92J2XEw2VLkJtL//NbRzy3vXzBiXO3yhIadGqqpl32KQWETRG03DJTMo/P/FbBaX9vIz1X+/+GFvYNSZRGjCXb0Q77e70T8xsldcCgYEAp8XdY818gyTl1utYMddlL2sqfgAHbhr8Mw/Qlx7Yjs/mu317javDg8fMW6xMQnXzX8o4adpwtTPelht8YWIW4ruISeAKxBmWfFawuGQa1NJuxR6e5EKrWL6NJzqHblZ9njv/YFJnf8C1UUevol3vhhYnJbL6+Qi6DH4+UhxuAQECgYA8CLcs6Ue+wrRL1GDxS2qTKqo4X9jFNeAhLCQU/6iL2i6R7vguW0ErfgQ8wbwBKY4ZpBDdCKpHTLuvwu0eQdjbpcF53LMjTXQXSt34iARN+AIH3jycSQwXAtMK5Opn66Rp17Mjq4Dn8cSMQNmrYefNyvDc26b5mkmesGP3wvA6Ew==",
                "json", "utf-8", "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhDW5DJPQWQ5UXlq7rfYhuYZ7zzztopDHxPrG3rD+KF0kStmwC/6BW5z6D6mtVZ3bu1eVxhQ/1UkAekXBmb4hTYSeKnrY7grR5/zrcdWdxepMI+4+4NUP5o4ItD5kKKC/kB8MsmCcLDwXjCVO/r9Y4ZPRCFsvfwXV7wJG2PKdPLCIn8jOyYXm5NrGbASdwFvYXo91aMahM7+YfU5Wccd428nmfHYPGjaSDt8qyXGVd1mAIR/haM0HWnLT56E60AsaPA/rNjH7upkh796MNw8yY6MIXgMhthThNHdIYJ7gzdMWjC8WYMHVOqXPr/Vwjh4PH4YCiHWYTqve9Uutmh+H9QIDAQAB", "RSA2");
        AlipayTradePagePayRequest request = getAlipayTradePagePayRequest(prescriptionId, totalPrice);

        try {
            AlipayTradePagePayResponse alipayTradePagePayResponse = alipayClient.pageExecute(request);
            return ResultResponse.success(alipayTradePagePayResponse.getBody());
        } catch (AlipayApiException e) {
            log.error(e.getMessage(), e);
            return ResultResponse.failure("Pay Failed");
        }
    }

    @GetMapping("GetPayState")
    public Result<Object> getPayState(String diagnosedHistoryId) {
        StringBuilder tmpDiagnosedHistoryId = new StringBuilder(diagnosedHistoryId);
        String prescriptionId = String.valueOf(tmpDiagnosedHistoryId.insert(8, "000"));
        Prescription prescription = prescriptionService.findPrescriptionById(prescriptionId);

        if (prescription == null) {
            return ResultResponse.failure("Not Found");
        }

        return ResultResponse.success(prescription.getPayState());
    }

    @GetMapping("GetDetailPre")
    public Result<Object> getDetailPre(String patientId) {
        List<TreatmentRecord> treatmentRecords = treatmentService.getTreatmentRecordsByPatientId(patientId);
        List<DiagnosedHistory> diagnosedHistories = new ArrayList<>();

        for (TreatmentRecord treatmentRecord : treatmentRecords) {
            DiagnosedHistory diagnosedHistory = diagnosedHistoryService.getDiagnosedHistory(treatmentRecord.getDiagnosedId());
            diagnosedHistories.add(diagnosedHistory);
        }

        if (diagnosedHistories.isEmpty()) {
            return ResultResponse.failure("Not Found");
        }

        Patient patient = patientService.getPatientById(patientId);

        if (patient == null) {
            return ResultResponse.failure("Not Found");
        }

        List<Record> records = new ArrayList<>();
        for (TreatmentRecord treatmentRecord : treatmentRecords) {
            Record record = new Record();
            record.setTreatmentRecord(treatmentRecord);
            Prescription prescription = prescriptionService.findPrescriptionById(treatmentRecord.getDiagnosedId().substring(0, 8) + "000" + treatmentRecord.getDiagnosedId().substring(8));
            record.setPrescription(prescription);

            List<PrescriptionMedicine> prescriptionMedicines = prescriptionMedicineService.getPrescriptionMedicineByPrescriptionId(prescription.getPrescriptionId());

            List<MedicineDescription> medicineDescriptions = new ArrayList<>();
            for (PrescriptionMedicine prescriptionMedicine : prescriptionMedicines) {
                MedicineDescription medicineDescription = medicineDescriptionService.findMedicineDescriptionByName(prescriptionMedicine.getMedicineName());

                medicineDescriptions.add(medicineDescription);
            }

            record.setMedicineDescriptions(medicineDescriptions);
            record.setDoctorInfo(doctorInfoService.findByDoctorId(treatmentRecord.getDoctorId()));

            records.add(record);
        }


        return ResultResponse.success(new Object[]{patient, records});
    }

    private static @NotNull AlipayTradePagePayRequest getAlipayTradePagePayRequest(String prescriptionId, BigDecimal totalPrice) {
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setNotifyUrl("http://localhost:9000/api/DiagnosedHistory/alipayNotify");
        request.setReturnUrl("http://localhost:9000/api/DiagnosedHistory/alipayReturn");

        AlipayTradePayModel model = new AlipayTradePayModel();
        model.setOutTradeNo(prescriptionId);
        model.setTotalAmount(String.valueOf(totalPrice));
        model.setSubject("药品收款");
        model.setProductCode("FAST_INSTANT_TRADE_PAY");

        request.setBizModel(model);
        return request;
    }
}
