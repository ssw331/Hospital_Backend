package com.ssw331.hospital.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

/**
 * @author 24053
 */
@Data
@Table("CONSULTING_ROOM")
public class ConsultingRoom {
    @TableId("CONSULTING_ROOM_NAME")
    private String consultingRoomName;
    @TableField("CONSULTANT_CAPACITY")
    private BigDecimal consultantCapacity;
}
