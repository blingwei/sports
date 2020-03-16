package com.yw.sports.service.Impl;


import com.yw.sports.dao.UserInfoMapper;
import com.yw.sports.dao.UserMapper;
import com.yw.sports.pojo.User;
import com.yw.sports.pojo.UserInfo;
import com.yw.sports.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public User findUserByName(String name) {
        return userMapper.findByName(name);
    }

    @Override
    public List<User> findAllUser() {
       return userMapper.selectAll();
    }

    @Override
    public User findUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteUser(Integer id) {
        userMapper.deleteByPrimaryKey(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addUser(User user) {
        userMapper.insert(user);
        userInfoMapper.insert(user.getId());
    }

    @Override
    public void updateUser(String name, User user) {

    }

    @Override
    public User getCurrentUser() {
        return findUserByName(SecurityUtils.getSubject().getPrincipal()+ "");
    }

    @Override
    public UserInfo getCurrentUserInfo() {
        Integer userId = getCurrentUser().getId();
        return userInfoMapper.selectByPrimaryKey(userId);
    }

    @Override
    public UserInfo getUserInoByUserName(String userName) {
        Integer userId = findUserByName(userName).getId();
        return userInfoMapper.selectByUserId(userId);
    }

    @Override
    public void editUser(UserInfo userInfo) {
        userInfoMapper.updateByPrimaryKey(userInfo);
    }
}
