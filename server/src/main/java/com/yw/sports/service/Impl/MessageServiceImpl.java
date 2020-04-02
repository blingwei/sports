package com.yw.sports.service.Impl;

import com.yw.sports.dao.MessageMapper;
import com.yw.sports.dao.UserMapper;
import com.yw.sports.pojo.Message;
import com.yw.sports.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void sendMessage(Message message) {
        messageMapper.delete(message);
        messageMapper.insert(message);
    }

}
