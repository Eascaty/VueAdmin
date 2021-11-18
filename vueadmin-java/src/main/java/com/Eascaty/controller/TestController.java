package com.Eascaty.controller;

import com.Eascaty.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    SysUserService UserService;

    @GetMapping("/test")
    public Object test(){
        return UserService.list();
    }

}
