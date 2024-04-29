package com.ssw331.hospital.controller;

import com.ssw331.hospital.dto.ChatRecord;
import com.ssw331.hospital.dto.serialization.Result;
import com.ssw331.hospital.dto.serialization.ResultResponse;
import com.ssw331.hospital.service.ChatRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author WWWsy
 */
@RestController
@RequestMapping("/api/chatrecords")
public class ChatRecordController {

    private final ChatRecordService chatRecordService;

    @Autowired
    public ChatRecordController(ChatRecordService chatRecordService) {
        this.chatRecordService = chatRecordService;
    }

    @GetMapping("/getChatRecord")
    public Result<Object> getChatRecord(@RequestParam String recordId) {
        List<ChatRecord> chatRecords = chatRecordService.findByRecordId(recordId);
        return ResultResponse.success(chatRecords);
    }

    @PostMapping("/addChatRecord")
    public Result<Object> addChatRecord(@RequestBody ChatRecord chatRecord) {
        boolean added = chatRecordService.addChatRecord(chatRecord);
        if (added) {
            return ResultResponse.success(chatRecord);
        } else {
            return ResultResponse.failure();
        }
    }
}