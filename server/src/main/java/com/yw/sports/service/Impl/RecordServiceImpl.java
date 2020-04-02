package com.yw.sports.service.Impl;

import com.yw.sports.dao.RecordMapper;
import com.yw.sports.dao.UserMapper;
import com.yw.sports.pojo.Record;
import com.yw.sports.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RecordMapper recordMapper;

    @Override
    public List<Record> getRecordsByUserId(Integer id, Integer start, Integer size) {
        return recordMapper.getRecordsByUserId(id, start, size);
    }

    @Override
    public Integer getRecordsNumsByUserId(Integer userId) {
        return recordMapper.getRecordsNumsByUserId(userId);
    }
}
