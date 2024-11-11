package com.example.sfm.controller;

import com.example.sfm.common.service.UserService;
import com.example.sfm.util.ResultUtil;
import com.example.sfm.vo.UpdatePasswordVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户管理控制器
 */
@RestController
@RequestMapping("/api/v1/user")
public class UserRestController {

    @Resource private UserService userService;

    /**
     * 获取当前登录的用户
     * 权限验证：
     *      当请求/api/v1/user/userInfo接口时，判断该用户是否拥有“DEFAULT”权限。
     */
    @PreAuthorize("hasAuthority('SFM_SYSTEM_USER')")
    @GetMapping("/userInfo")
    public ResultUtil getUserInfo(){
        return ResultUtil.success(userService.getUserInfo());
    }

    /**
     * 修改当前登录账号的信息
     * @param updateInfo
     * @return
     */
    @PreAuthorize("hasAuthority('SFM_SYSTEM_UPDATE_INFO')")
    @PostMapping("/update/currentLoginUser")
    public ResultUtil updateCurrentLoginUser(@RequestBody Map<String, Object> updateInfo){
        userService.updateCurrentLoginUser(updateInfo);
        return ResultUtil.success();
    }

    /**
     * 修改当前登录账号的密码
     * @param updatePasswordVo
     * @return
     */
    @PreAuthorize("hasAuthority('SFM_SYSTEM_UPDATE_PWD')")
    @PatchMapping("/update/currentLoginPwd")
    public ResultUtil updateCurrentLoginPwd(@RequestBody UpdatePasswordVo updatePasswordVo){
        userService.updateCurrentLoginPwd(updatePasswordVo);
        return ResultUtil.success();
    }

}
