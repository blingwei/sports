package com.yw.sports.dao;

import com.yw.sports.pojo.Condition;

import java.util.List;

public interface ConditionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Condition record);

    Condition selectByPrimaryKey(Integer id);

    List<Condition> selectAll();

    int updateByPrimaryKey(Condition record);
}