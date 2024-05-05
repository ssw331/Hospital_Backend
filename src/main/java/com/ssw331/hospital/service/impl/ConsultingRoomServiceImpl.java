package com.ssw331.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ssw331.hospital.dto.ConsultingRoom;
import com.ssw331.hospital.mapper.ConsultingRoomMapper;
import com.ssw331.hospital.service.ConsultingRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 24053
 */
@Component
public class ConsultingRoomServiceImpl implements ConsultingRoomService {
    private final ConsultingRoomMapper consultingRoomMapper;

    @Autowired
    public ConsultingRoomServiceImpl(ConsultingRoomMapper consultingRoomMapper) {
        this.consultingRoomMapper = consultingRoomMapper;
    }

    @Override
    public List<ConsultingRoom> getAllConsultingRooms() {
        return consultingRoomMapper.selectList(new QueryWrapper<>());
    }
}
