package com.ssw331.hospital.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ssw331.hospital.dto.ChatRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;
import java.util.List;


/**
 * @author WWWsy
 */
@Mapper
public interface ChatRecordMapper extends BaseMapper<ChatRecord> {
    /**
     * 根据时间戳检查记录是否存在
     *
     * @param timestamp 时间戳
     * @return 找到的记录数量
     */
    @Select("SELECT COUNT(*) FROM chat_record WHERE timestamp = #{timestamp}")
    int countByTimestamp(Timestamp timestamp);

    /**
     * 根据记录ID查询聊天记录
     *
     * @param recordId 聊天记录ID
     * @return 一组聊天记录列表，按照时间戳进行排序
     */
    @Select("SELECT * FROM chat_record WHERE recordid = #{recordId} ORDER BY timestamp")
    List<ChatRecord> selectByRecordId(@Param("recordId") String recordId);
}
