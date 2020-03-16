package com.yw.sports.service;


import com.yw.sports.pojo.User;
import com.yw.sports.pojo.UserInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User findUserByName(String name);
    List<User> findAllUser();
    User findUserById(Integer id);
    void deleteUser(Integer id);
    void addUser(User user);
    void updateUser(String name, User user);
    User getCurrentUser();
    UserInfo getCurrentUserInfo();
    UserInfo getUserInoByUserName(String userName);
    void editUser(UserInfo userInfo);
}
