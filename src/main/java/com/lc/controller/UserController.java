package com.lc.controller;

import com.lc.service.UserServerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServerImpl userServerImpl;

    @RequestMapping("/login")
    public String login( String userName, String userPsd) {
        try {
            userServerImpl.login(userName, userPsd);
            System.out.println("登录成功...");
            return "index";
        }
        catch (Exception e){
            System.out.println("登录失败...");
            return "login";
        }
    }
}
