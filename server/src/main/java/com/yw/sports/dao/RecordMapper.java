package com.yw.sports.dao;

import com.yw.sports.pojo.Record;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Record record);

    Record selectByPrimaryKey(Integer id);

    List<Record> selectAll();

    int updateByPrimaryKey(Record record);

    List<Record> getRecordsByUserId(@Param("id") Integer id, @Param("start") Integer start, @Param("size") Integer size);

    Integer getRecordsNumsByUserId(Integer userId);
}