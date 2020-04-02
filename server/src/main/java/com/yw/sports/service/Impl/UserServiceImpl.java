package com.yw.sports.service.Impl;


import com.yw.sports.bean.responseBean.CoachesResponse;
import com.yw.sports.bean.responseBean.GetMyCoachResponse;
import com.yw.sports.bean.responseBean.MyUsersResponse;
import com.yw.sports.dao.MessageMapper;
import com.yw.sports.dao.UserInfoMapper;
import com.yw.sports.dao.UserMapper;
import com.yw.sports.pojo.Message;
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

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public User findUserByName(String name) {
        return userMapper.findByName(name);
    }

    @Override
    public List<User> findAllUser(Integer role) {
       return userMapper.selectAll(role);
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

    @Override
    public List<CoachesResponse> getCoaches(Integer start, Integer size, String input) {
        List<CoachesResponse> res = userMapper.getCoaches(start, size, input);
        for(CoachesResponse coachesResponse: res){
            if("1".equals(coachesResponse.getSex())){
                coachesResponse.setSex("女性");
            }else{
                coachesResponse.setSex("男性");
            }
            coachesResponse.setStatus("火热");
        }
        return res;
    }

    @Override
    public Integer getCoacheNums(String input) {
        return userMapper.getCoacheNums(input);
    }

    @Override
    public GetMyCoachResponse getMyCoach() {
        GetMyCoachResponse res = new GetMyCoachResponse();
        Integer id = getCurrentUser().getId();
        User coach = userMapper.getCoachById(id);
        res.setName(coach.getUsername());
        res.setSuggest(messageMapper.getSuggest(id, coach.getId()));
        return res;
    }


    @Override
    public void selectCoach(int id) {
        int userId = getCurrentUser().getId();
        userMapper.deleteCoach(userId, id);
        userMapper.addCoach(userId, id);
    }

    @Override
    public List<MyUsersResponse> getMyUsers() {
        Integer cocahId = getCurrentUser().getId();
        List<MyUsersResponse>  res = userMapper.getMyUsers(cocahId);
        return res;
    }
}
