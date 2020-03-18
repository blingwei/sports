package com.yw.sports.dao;

import com.yw.sports.pojo.Message;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Message record);

    Message selectByPrimaryKey(Integer id);

    List<Message> selectAll();

    int updateByPrimaryKey(Message record);

    String getSuggest(@Param("userId") Integer userId, @Param("CoachId") Integer CoachId);
}