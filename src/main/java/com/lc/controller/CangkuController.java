package com.lc.controller;


import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cangku")
public class CangkuController {

    //（入库）去到新增仓库界面
    @RequestMapping("/c_add")
    @RequiresPermissions("sys:c:save")  //拥有 sys:c:save 权限才能够跳转
    public String toMain() {
        return "cangkuViews/c_add";
    }

    //（出库）去到展示仓库数据到list ，在展示界面进行按钮删除
    @RequestMapping("/list")
    public String cangluList() {
        return "cangkuViews/lsit";
    }

}
