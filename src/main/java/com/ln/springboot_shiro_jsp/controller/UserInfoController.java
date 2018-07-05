package com.ln.springboot_shiro_jsp.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/userInfo")
public class UserInfoController {

    @RequestMapping("/userList")
    @RequiresPermissions("userInfo")
    public String userInfo(){
        return "userInfo/userInfo";
    }

    @RequestMapping("/userAdd")
    @RequiresPermissions("userInfo")
    public String userAdd(){
        return "userInfo/userInfoAdd";
    }

    @RequestMapping("/userDel")
    @RequiresPermissions("del")
    public String userDel(){
        return "userInfo/userInfoDel";
    }

    @PostMapping("/getData")
    @ResponseBody
    @RequiresPermissions("userInfossss")
    public Map getData(){
        Map map = new HashMap();
        map.put("a", "aaa");
        return map;
    }
}