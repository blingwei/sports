package com.yw.sports.dao;

import com.yw.sports.bean.responseBean.CoachesResponse;
import com.yw.sports.bean.responseBean.GetMyCoachResponse;
import com.yw.sports.bean.responseBean.MyUsersResponse;
import com.yw.sports.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll(Integer role);

    int updateByPrimaryKey(User record);

    User findByName(String name);

    List<CoachesResponse> getCoaches(@Param("start") Integer start, @Param("size") Integer size, @Param("input") String input);

    Integer getCoacheNums(@Param("input") String input);

    User getCoachById(Integer id);

    void deleteCoach(@Param("userId") int userId, @Param("id") int id);

    void addCoach(@Param("userId") int userId, @Param("id") int id);

    List<MyUsersResponse> getMyUsers(Integer cocahId);
}