package com.example.sfm.common.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.sfm.common.vo.MenuInfo;
import com.example.sfm.exception.DangerException;
import com.example.sfm.mapper.SFMStudentMapper;
import com.example.sfm.mapper.SFMTeacherMapper;
import com.example.sfm.pojo.SFMStudent;
import com.example.sfm.pojo.SFMTeacher;
import com.example.sfm.service.SFMStudentService;
import com.example.sfm.vo.UpdatePasswordVo;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author lihai
 * Create Date: 2020-10-04
 */
@Service
public class UserService {

    @Resource private SFMStudentMapper sfmStudentMapper;
    @Resource private SFMTeacherMapper sfmTeacherMapper;

    public static Authentication getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getPrincipal() == null) {
            return null;
        }
        return authentication;
    }

    public <T> T getCurrentLoginUser(T t) {
        Authentication authentication = getAuthentication();
        if (authentication == null) return null;

        String loginName = authentication.getName();

        if (t instanceof SFMStudent) {
            LambdaQueryWrapper<SFMStudent> lambdaQueryWrapper = Wrappers.lambdaQuery();
            lambdaQueryWrapper.eq(SFMStudent::getNumber, loginName);
            t = (T)sfmStudentMapper.selectOne(lambdaQueryWrapper);
        } else if (t instanceof SFMTeacher) {
            LambdaQueryWrapper<SFMTeacher> lambdaQueryWrapper = Wrappers.lambdaQuery();
            lambdaQueryWrapper.eq(SFMTeacher::getNumber, loginName);
            t = (T)sfmTeacherMapper.selectOne(lambdaQueryWrapper);
        }

        return t;
    }

    /**
     * 修改当前登录账号的信息
     * @param info
     */
    public void updateCurrentLoginUser(Map<String, Object> info) {
        Authentication authentication = getAuthentication();
        if (authentication == null) return;

        String loginName = authentication.getName();

        if (loginName.startsWith("SD")) {
            SFMStudent currentLoginUser = getCurrentLoginUser(new SFMStudent());
            if (info.containsKey("name")) {
                currentLoginUser.setName(MapUtils.getString(info, "name"));
            }
            if (info.containsKey("gender")) {
                currentLoginUser.setGender(MapUtils.getString(info, "gender"));
            }
            if (info.containsKey("nation")) {
                currentLoginUser.setNation(MapUtils.getString(info, "nation"));
            }
            if (info.containsKey("birthdate")) {
                try {
                    currentLoginUser.setBirthdate(DateUtils.parseDate((String) MapUtils.getObject(info, "birthdate"), "yyyy-MM-dd"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if (info.containsKey("nativePlace")) {
                currentLoginUser.setNativePlace(MapUtils.getString(info, "nativePlace"));
            }
            if (info.containsKey("idCard")) {
                currentLoginUser.setIdCard(MapUtils.getString(info, "idCard"));
            }
            if (info.containsKey("bareheadedPhoto")) {
                currentLoginUser.setBareheadedPhoto(MapUtils.getString(info, "bareheadedPhoto"));
            }
            if (info.containsKey("homeAddress")) {
                currentLoginUser.setHomeAddress(MapUtils.getString(info, "homeAddress"));
            }
            currentLoginUser.updateById();
        } else if (loginName.startsWith("TC")) {
            SFMTeacher currentLoginUser = getCurrentLoginUser(new SFMTeacher());
            if (info.containsKey("name")) {
                currentLoginUser.setName(MapUtils.getString(info, "name"));
            }
            currentLoginUser.updateById();
        }

    }

    /**
     * 修改当前登录账号的密码
     * @param updatePasswordVo
     */
    public void updateCurrentLoginPwd(UpdatePasswordVo updatePasswordVo) {
        // 获取当前登录的用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getPrincipal() == null) {
            return;
        }

        String loginName = authentication.getName();

        // 登录账号SD开头表示学生，TC开头表示教师，admin为系统内置管理员。
        if (loginName.startsWith("SD")) {
            SFMStudent currentLoginUser = getCurrentLoginUser(new SFMStudent());
            if (updatePasswordVo.getOldPassword().equals(currentLoginUser.getPassword())) {
                currentLoginUser.setPassword(updatePasswordVo.getNewPassword());
                currentLoginUser.updateById();
            } else {
                throw new DangerException("旧的密码错误");
            }
        } else if (loginName.startsWith("TC")) {
            SFMTeacher currentLoginUser = getCurrentLoginUser(new SFMTeacher());
            if (updatePasswordVo.getOldPassword().equals(currentLoginUser.getPassword())) {
                currentLoginUser.setPassword(updatePasswordVo.getNewPassword());
                currentLoginUser.updateById();
            } else {
                throw new DangerException("旧的密码错误");
            }
        }

    }

    public Map<String, Object> getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getPrincipal() == null) {
            return null;
        }
        String loginName = authentication.getName();
        String username = authentication.getName();
        Object info = null;
        if (loginName.startsWith("SD")) {
            SFMStudent sfmStudent = getCurrentLoginUser(new SFMStudent());
            username = sfmStudent.getName();
            info = sfmStudent;
        } else if (loginName.startsWith("TC")) {
            SFMTeacher sfmTeacher = getCurrentLoginUser(new SFMTeacher());
            username = sfmTeacher.getName();
            info = sfmTeacher;
        }

        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("username", username); // 用户名
        userInfo.put("userInfo", info); // 用户信息
        userInfo.put("authorities", authentication.getAuthorities()); // 系统权限

        return userInfo;
    }
}
