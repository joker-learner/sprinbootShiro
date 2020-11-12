package com.lc.controller;

import com.lc.mapper.UserMapper;
import com.lc.service.impl.UserServerImpl;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServerImpl userServerImpl;
    @Autowired
    private UserMapper userMapper;

    //登录
    @RequestMapping("/login")
    public String login(String userName, String userPsd) {
        try {
            userServerImpl.login(userName, userPsd);
            System.out.println("登录成功...");
            return "index";
        } catch (Exception e) {
            System.out.println("登录失败...");
            return "login";
        }
    }

    //注册
    @RequestMapping("/register")
    public String toIndex(String userName, String userPsd) {

//        int rand = new Random().nextInt(10000);  //随机盐
        Md5Hash md5Hash = new Md5Hash(userPsd);
//        System.out.println(rand);
        userMapper.insertIntoSalt(userName, "");
        userMapper.createUser(userName , md5Hash.toString());
        System.out.println("注册成功...");
        return "login";   //跳到登录界面
    }

}
