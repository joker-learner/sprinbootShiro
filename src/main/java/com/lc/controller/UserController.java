package com.lc.controller;

import com.lc.service.UserServerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServerImpl userServerImpl;

    @RequestMapping("/login")
    public String login(String userName, String userPsd) {
        //加盐加密
//        int rand = new Random().nextInt(10000);  //随机盐
//        Md5Hash md5Hash = new Md5Hash(userPsd, rand);
//        System.out.println(md5Hash.toHex());

        try {
            userServerImpl.login(userName, userPsd);
            System.out.println("登录成功...");
            return "index";
        } catch (Exception e) {
            System.out.println("登录失败...");
            return "login";
        }
    }
}
