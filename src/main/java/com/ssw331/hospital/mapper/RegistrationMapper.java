package com.ssw331.hospital.mapper;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ssw331.hospital.dto.Registration;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * @author WWWsy
 */
@Mapper
public interface RegistrationMapper extends BaseMapper<Registration> {
    default void updateRegistration(Registration registration) {
        UpdateWrapper<Registration> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("patient_id", registration.getPatientId())
                .eq("doctor_id", registration.getDoctorId())
                .eq("appointment_time", registration.getAppointmentTime())
                .eq("period", registration.getPeriod())
                .eq("state", 0);

        registration.setState(new BigDecimal(1));
        update(registration, updateWrapper);
    }
}
