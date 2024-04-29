package com.ssw331.hospital.service;

import com.ssw331.hospital.dto.ChatRecord;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author WWWsy
 */
@Service
public interface ChatRecordService {
    /**
     * 根据聊天记录ID找到一组聊天记录
     *
     * @param recordId 聊天记录ID
     * @return 一组聊天记录的列表
     */
    List<ChatRecord> findByRecordId(String recordId);
    /**
     * 添加一条聊天记录
     *
     * @param chatRecord 一条聊天记录
     * @return 插入成功返回true
     */
    boolean addChatRecord(ChatRecord chatRecord);
}