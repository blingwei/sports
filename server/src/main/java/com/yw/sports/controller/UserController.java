package com.yw.sports.controller;


import com.yw.sports.bean.commanBean.OperateBean;
import com.yw.sports.pojo.User;
import com.yw.sports.pojo.UserInfo;
import com.yw.sports.result.Result;
import com.yw.sports.service.UserService;
import com.yw.sports.utils.ResultFactory;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("login")
    public Result login(@RequestBody User user){
        String name = user.getUsername();
        name = HtmlUtils.htmlEscape(name);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(name, user.getPassword());
        try{
            subject.login(usernamePasswordToken);
            if(user.getRole().equals(userService.findUserByName(usernamePasswordToken.getUsername()).getRole()) ){
                return ResultFactory.buildSuccessResult("登录成功", userService.findUserByName(usernamePasswordToken.getUsername()));
            }else{
                return ResultFactory.buildFailResult("账号或密码错误");
            }
        }catch (Exception e){
            return ResultFactory.buildFailResult("账号或密码错误");
        }
    }

    @PostMapping("register")
    public Result register(@RequestBody User user){
        String name = user.getUsername();
        String pass = user.getPassword();
        name = HtmlUtils.htmlEscape(name);
        user.setUsername(name);
        if(userService.findUserByName(name)!=null){
            return ResultFactory.buildFailResult("用户名已存在");
        }
        //随机生成16位的盐
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        String passInDB = new SimpleHash("md5", pass, salt, 2).toString();
        user.setPassword(passInDB);
        user.setSalt(salt);
        userService.addUser(user);
        return ResultFactory.buildSuccessResult("注册成功", null);
    }

    @GetMapping("logout")
    public Result logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return ResultFactory.buildSuccessResult("注销成功", null);
}

    @GetMapping(value = "authentication")
    public String authentication(){
        if(SecurityUtils.getSubject().getPrincipal()!=null){
            return "身份认证成功";
        }
        return null;
    }

    @GetMapping("getUserInfo")
    public Result getUserInfo(@Param("userName" )String userName){
        UserInfo userInfo = userService.getUserInoByUserName(userName);
        return ResultFactory.buildSuccessResult("", userInfo);
    }

    @GetMapping("getCoaches")
    public Result getCoaches(@Param("start") Integer start, @Param("size") Integer size, @Param("input") String input){
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("coaches",userService.getCoaches(start, size, input));
        resultMap.put("nums", userService.getCoacheNums(input));
        return ResultFactory.buildSuccessResult("", resultMap);
    }

    @GetMapping("getMyCoach")
    public Result getMyCoach(){
        return ResultFactory.buildSuccessResult("", userService.getMyCoach());
    }

    @GetMapping("getMyUsers")
    public Result getMyUsers(){
        return ResultFactory.buildSuccessResult("", userService.getMyUsers());
    }

    @PostMapping("selectCoach")
    public Result selectCoach(@RequestBody OperateBean operateBean){
        try{
            userService.selectCoach(operateBean.getId());
            return ResultFactory.buildSuccessResult("修改成功", null);
        }catch (Exception e){
            e.printStackTrace();
            return ResultFactory.buildFailResult(e.getMessage());
        }
    }


    @PostMapping("editUserInfo")
    public Result editUserInfo(@RequestBody UserInfo userInfo){
        try{
            userService.editUser(userInfo);
            return ResultFactory.buildSuccessResult("修改成功", null);
        }catch (Exception e){
            e.printStackTrace();
            return ResultFactory.buildFailResult(e.getMessage());
        }
    }

//    @GetMapping("findUserByUserName")
//    public Result findUserByUserName( @Param("name") String name){
//        List<FindByInputResponse> res = new ArrayList<>();
//        for(User user: userService.findUserByNameLikely(name)){
//            FindByInputResponse item = new FindByInputResponse();
//            item.setValue(user.getUsername());
//            item.setObject(user);
//            res.add(item);
//        }
//        return ResultFactory.buildSuccessResult(null, res);
//    }


}
