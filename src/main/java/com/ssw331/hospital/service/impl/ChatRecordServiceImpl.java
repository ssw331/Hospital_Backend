package com.ssw331.hospital.service.impl;

import com.ssw331.hospital.dto.ChatRecord;
import com.ssw331.hospital.mapper.ChatRecordMapper;
import com.ssw331.hospital.service.ChatRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author WWWsy
 */
@Component
public class ChatRecordServiceImpl implements ChatRecordService {
    @Autowired
    private final ChatRecordMapper chatRecordMapper;


    public ChatRecordServiceImpl(ChatRecordMapper chatRecordMapper) {
        this.chatRecordMapper = chatRecordMapper;
    }

    @Override
    public List<ChatRecord> findByRecordId(String recordId) {
        return chatRecordMapper.selectByRecordId(recordId);
    }

    @Override
    public boolean addChatRecord(ChatRecord chatRecord) {
        // 检查聊天记录是否存在
        int exists = chatRecordMapper.countByTimestamp(chatRecord.getTimeStamp());
        if (exists==0) {
            // 不存在就插入一条新的记录
            chatRecordMapper.insert(chatRecord);
            return true;
        }
        return false;
    }
}