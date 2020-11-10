package com.lc.controller;

import com.lc.mapper.UserMapper;
import com.lc.pojo.Users;
import com.lc.service.UserServerImpl;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Random;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServerImpl userServerImpl;
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/login")
    public String login(String userName, String userPsd) {
//        System.out.println(userName + userPsd);
        try {
            userServerImpl.login(userName, userPsd);
            System.out.println("登录成功...");
            return "index";
        } catch (Exception e) {
            System.out.println("登录失败...");
            return "login";
        }
    }

    @RequestMapping("/rigister")
    public String toIndex(String userName, String userPsd) {
        int rand = new Random().nextInt(10000);  //随机盐
        Md5Hash md5Hash = new Md5Hash(userPsd, rand+"");
        userMapper.insertIntoSalt(userName, rand+"");
        System.out.println("注册成功...");
        return "index";
    }
}
