package com.yw.sports.service;

import com.yw.sports.pojo.Message;
import org.apache.ibatis.annotations.Param;

/**
 * @author 86187
 */
public interface MessageService {

    void sendMessage(Message message);


}
