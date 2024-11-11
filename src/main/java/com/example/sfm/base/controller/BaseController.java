package com.example.sfm.base.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 主页面跳转控制器
 */
@Controller
public class BaseController {

    /**
     * 跳转到用户首页
     * 权限验证：
     *      当请求/接口时，判断该用户是否拥有“DEFAULT”权限（多个权限使用hasAnyAuthority）。
     */
    @GetMapping("/")
    @PreAuthorize("hasAuthority('SFM_SYSTEM_USER')")
    public String index(){
        return "index";
    }

    /**
     * 跳转到用户登录页面
     */
    @GetMapping("/login")
    public String login(){
        return "login";
    }

}
