package com.example.sfm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 跳转控制器
 */
@Controller
@RequestMapping("/web")
public class WebController {

    /**
     * 页面跳转
     */
    @GetMapping("/admin/user-setting")
    public String userSetting() {
        return "admin/student-setting";
    }

    @GetMapping("/admin/user-password")
    public String userPassword() {
        return "admin/teacher-password";
    }

    @GetMapping("/admin/setting")
    public String setting() {
        return "admin/setting";
    }

    @GetMapping("/404")
    public String notFound() {
        return "404";
    }

    @GetMapping("/admin/organization/collegeManagement")
    public String collegeManagement() {
        return "admin/organization/collegeManagement";
    }

    @GetMapping("/admin/organization/collegeAdd")
    public String collegeAdd() {
        return "admin/organization/collegeAdd";
    }

    @GetMapping("/admin/organization/specialtyManagement")
    public String specialtyManagement() {
        return "admin/organization/specialtyManagement";
    }

    @GetMapping("/admin/organization/specialtyAdd")
    public String specialtyAdd() {
        return "admin/organization/specialtyAdd";
    }

    @GetMapping("/admin/organization/teacherManagement")
    public String teacherManagement() {
        return "admin/organization/teacherManagement";
    }

    @GetMapping("/admin/organization/teacherAdd")
    public String teacherAdd() {
        return "admin/organization/teacherAdd";
    }

    @GetMapping("/admin/organization/classManagement")
    public String classManagement() {
        return "admin/organization/classManagement";
    }

    @GetMapping("/admin/organization/classAdd")
    public String classAdd() {
        return "admin/organization/classAdd";
    }

    @GetMapping("/admin/student/studentInfo")
    public String studentInfo() {
        return "admin/student/studentInfo";
    }

    @GetMapping("/admin/student/studentAdd")
    public String studentAdd() {
        return "admin/student/studentAdd";
    }

    @GetMapping("/admin/student/educationExperience")
    public String educationExperience() {
        return "admin/student/educationExperience";
    }

    @GetMapping("/admin/student/educationExperienceAdd")
    public String educationExperienceAdd() {
        return "admin/student/educationExperienceAdd";
    }

    @GetMapping("/admin/student/rewardPunishment")
    public String rewardPunishment() {
        return "admin/student/rewardPunishment";
    }

    @GetMapping("/admin/student/rewardPunishmentAdd")
    public String rewardPunishmentAdd() {
        return "admin/student/rewardPunishmentAdd";
    }

}
