package com.yw.sports.service;

import com.yw.sports.pojo.Record;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RecordService {
    List<Record> getRecordsByUserId(Integer id, Integer start, Integer size);

    Integer getRecordsNumsByUserId(Integer userId);
}
