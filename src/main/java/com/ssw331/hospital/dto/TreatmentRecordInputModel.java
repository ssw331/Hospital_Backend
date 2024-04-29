package com.ssw331.hospital.dto;

import lombok.Getter;

/**
 * @author WWWsy
 */
@Getter
public class TreatmentRecordInputModel {
    private String patientId;
    private String doctorId;
    private int period;
    private String selfReported;
    private String presentHis;
    private String anamnesis;
    private String sign;
    private String clinicDia;
    private String advice;
    private String medicine;

}
