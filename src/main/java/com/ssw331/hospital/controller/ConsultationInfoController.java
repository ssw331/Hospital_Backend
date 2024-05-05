package com.ssw331.hospital.controller;

import com.ssw331.hospital.dto.ConsultationInfo;
import com.ssw331.hospital.dto.ConsultingRoom;
import com.ssw331.hospital.dto.DoctorInfo;
import com.ssw331.hospital.dto.serialization.Result;
import com.ssw331.hospital.dto.serialization.ResultResponse;
import com.ssw331.hospital.service.ConsultationInfoService;
import com.ssw331.hospital.service.ConsultingRoomService;
import com.ssw331.hospital.service.DoctorInfoService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 24053
 */
@RestController
@RequestMapping(path = "/api/consultation_info")
public class ConsultationInfoController {

    @Getter
    @Setter
    private class ConsultationInfoApplied{
        String doctorId;
        String clinicName;
        Timestamp dateTime;
        String startTime;
        String endTime;

        public ConsultationInfoApplied(String doctorId, String clinicName, Timestamp dateTime, String startTime, String endTime){
            this.doctorId = doctorId;
            this.clinicName = clinicName;
            this.dateTime = dateTime;
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }


    private final ConsultationInfoService consultationInfoService;
    private final DoctorInfoService doctorInfoService;
    private final ConsultingRoomService consultingRoomService;

    @Autowired
    public ConsultationInfoController(ConsultationInfoService consultationInfoService, DoctorInfoService doctorInfoService, ConsultingRoomService consultingRoomService) {
        this.consultationInfoService = consultationInfoService;
        this.doctorInfoService = doctorInfoService;
        this.consultingRoomService = consultingRoomService;
    }

    @GetMapping
    public Result<Object> getConsultationInfoByDepartmentAndKeyword(String department, String keyword) {
        List<DoctorInfo> doctorInfos = doctorInfoService.findByDepartmentName(department);
        if (keyword != null && !keyword.isEmpty()) {
            doctorInfos.removeIf(doctorInfo -> !doctorInfo.getName().contains(keyword));
        }
        if (doctorInfos.isEmpty()) {
            // doctor infos empty
            return ResultResponse.failure();
        }
        List<ConsultationInfo> consultationInfos = new ArrayList<>();
        for (DoctorInfo doctorInfo : doctorInfos) {
            consultationInfos.addAll(consultationInfoService.findByDoctorId(doctorInfo.getDoctorId()));
        }
        if (consultationInfos.isEmpty()) {
            return ResultResponse.failure();
        }

        return ResultResponse.success(consultationInfos);
    }

    @GetMapping("AllConsultInfo")
    public Result<Object> getAllConsultationInfo() {
        List<ConsultationInfo> result = consultationInfoService.findAll();
        if (result.isEmpty()) {return ResultResponse.success(result);}

        List<ConsultationInfoApplied> finalRes = new ArrayList<>();
        for (ConsultationInfo consultationInfo : result) {
            ConsultationInfoApplied consultationInfoApplied = new ConsultationInfoApplied(
                    consultationInfo.getDoctorId(),
                    consultationInfo.getClinicName(),
                    consultationInfo.getDateTime(),
                    getStartTime(consultationInfo.getPeriod().intValue()),
                    getEndTime(consultationInfo.getPeriod().intValue())
                    );
            finalRes.add(consultationInfoApplied);
        }

        return ResultResponse.success(finalRes);
    }

    @PutMapping("ChangeConsult")
    public Result<Object> changeConsultationInfo(@RequestBody ConsultationInfo oldConsultationInfo, @RequestBody ConsultationInfo newConsultationInfo) {
        ConsultationInfo oldConsult = consultationInfoService.findByAll(oldConsultationInfo);
        ConsultationInfo existedConsult = consultationInfoService.findByAll(newConsultationInfo);
        if (oldConsult == null || existedConsult != null) {
            // old one not existed, new one has existed
            return ResultResponse.failure();
        }

        try {
            if (consultationInfoService.save(newConsultationInfo) < 1) {
                throw new Exception();
            }
            if (consultationInfoService.delete(oldConsultationInfo) < 1) {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return ResultResponse.success("saved consultation info");
    }

    @PutMapping("CancelConsult")
    public Result<Object> cancelConsultationInfo(@RequestBody ConsultationInfo consultationInfo) {
        ConsultationInfo existedConsult = consultationInfoService.findByAll(consultationInfo);
        if (existedConsult == null) {
            // not existed
            return ResultResponse.failure();
        }

        consultationInfoService.delete(consultationInfo);
        return ResultResponse.success("cancelled consultation info");
    }

    @PostMapping("AddConsult")
    public Result<Object> addConsultationInfo(@RequestBody ConsultationInfo consultationInfo) {
        ConsultationInfo existedConsult = consultationInfoService.findByAll(consultationInfo);
        if (existedConsult != null) {
            // existed consult
            return ResultResponse.failure();
        }

        consultationInfoService.save(consultationInfo);

        return ResultResponse.success("added consultation info");
    }

    @GetMapping("GetAllRoom")
    public Result<Object> getAllRoom() {
        List<ConsultingRoom> consultingRooms = consultingRoomService.getAllConsultingRooms();
        return ResultResponse.success(consultingRooms);
    }

    private static String getStartTime(int period)
    {
        return switch (period) {
            case 1 -> "08:00";
            case 2 -> "09:00";
            case 3 -> "10:00";
            case 4 -> "13:00";
            case 5 -> "14:00";
            case 6 -> "15:00";
            case 7 -> "16:00";
            default -> "Unknown";
        };
    }

    private static String getEndTime(int period)
    {
        return switch (period) {
            case 1 -> "09:00";
            case 2 -> "10:00";
            case 3 -> "11:00";
            case 4 -> "14:00";
            case 5 -> "15:00";
            case 6 -> "16:00";
            case 7 -> "17:00";
            default -> "Unknown";
        };
    }
}
