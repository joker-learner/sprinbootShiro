package com.lc.service.impl;

import com.lc.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

@Service
public class UserServerImpl implements UserService {

    //登录认证
    public void login(String userName, String userPsd) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, userPsd);
        subject.login(token);
    }

}
