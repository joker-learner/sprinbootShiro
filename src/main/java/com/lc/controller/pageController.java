package com.lc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class pageController {

    @RequestMapping("/")
    public String login(){
        return "login";
    }

    @RequestMapping("/login")
    public String login2(){
        return "login";
    }
    @RequestMapping("/layim")    //调到LayIm.html
    public String toLayUi(){
        return "LayIm";
    }

    @RequestMapping("/torigister")  //调到register.html界面
    public String toRigister(){
        return "register";
    }

}
