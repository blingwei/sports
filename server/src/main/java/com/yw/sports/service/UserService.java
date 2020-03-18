package com.yw.sports.service;


import com.yw.sports.bean.responseBean.CoachesResponse;
import com.yw.sports.bean.responseBean.GetMyCoachResponse;
import com.yw.sports.bean.responseBean.MyUsersResponse;
import com.yw.sports.pojo.User;
import com.yw.sports.pojo.UserInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User findUserByName(String name);
    List<User> findAllUser(Integer role);
    User findUserById(Integer id);
    void deleteUser(Integer id);
    void addUser(User user);
    void updateUser(String name, User user);
    User getCurrentUser();
    UserInfo getCurrentUserInfo();
    UserInfo getUserInoByUserName(String userName);
    void editUser(UserInfo userInfo);

    List<CoachesResponse>  getCoaches(Integer start, Integer size, String input);

    Integer getCoacheNums(String input);

    GetMyCoachResponse getMyCoach();

    void selectCoach(int id);

    MyUsersResponse getMyUsers();
}
