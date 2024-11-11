package com.example.sfm.enumeration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lihai
 * 该系统有三种权限：学生、教师、系统管理员
 * Create Date: 2020-10-03
 */
public enum Authority {

    /**
     * 学院管理：
     *  学生：无
     *  教师：查询所有学院信息
     *  管理员：全部权限（增删改查）
     *
     * 专业管理：
     *  学生：无
     *  教师：查询所有专业信息
     *  管理员：全部权限（增删改查）
     *
     * 教师管理：
     *  学生：无
     *  教师：无
     *  管理员：全部权限（增删改查）
     *
     *  班级管理：
     *  学生：无
     *  教师：查询所有班级信息
     *  管理员：全部权限（增删改查）
     *
     * 学生信息：
     *  学生：查看、修改个人信息
     *  教师：查询所有学生信息
     *  管理员：全部权限（增删改查）
     *
     * 奖惩记录：
     *  学生：查询本人惩戒记录
     *  教师：查询所有奖惩记录
     *  管理员：全部权限（增删改查）
     *
     * 教育经历：
     *  学生：查询本人教育经历
     *  教师：查询所有的教育经历
     *  管理员：全部权限（增删改查）
     */

    SFM_SYSTEM_USER("文档管理系统-用户"),
    SFM_SYSTEM_ADMIN("文档管理系统-管理员"),
    SFM_SYSTEM_TEACHER("文档管理系统-教师"),
    SFM_SYSTEM_STUDENT("文档管理系统-学生"),
    SFM_SYSTEM_UPDATE_PWD("修改密码"),
    SFM_SYSTEM_UPDATE_INFO("修改个人信息"),

    //学院管理
    ORGANIZATION_COLLEGE_READ("组织管理-系统管理-学院查看"),
    ORGANIZATION_COLLEGE_CREATE("组织管理-系统管理-学院创建"),
    ORGANIZATION_COLLEGE_UPDATE("组织管理-系统管理-学院更新"),
    ORGANIZATION_COLLEGE_DELETE("组织管理-系统管理-学院删除"),

    //专业管理
    ORGANIZATION_SPECIALTY_READ("组织管理-专业管理-专业查看"),
    ORGANIZATION_SPECIALTY_CREATE("组织管理-专业管理-专业创建"),
    ORGANIZATION_SPECIALTY_UPDATE("组织管理-专业管理-专业更新"),
    ORGANIZATION_SPECIALTY_DELETE("组织管理-专业管理-专业删除"),

    //教师管理
    ORGANIZATION_TEACHER_READ("组织管理-教师管理-教师查看"),
    ORGANIZATION_TEACHER_CREATE("组织管理-教师管理-教师创建"),
    ORGANIZATION_TEACHER_UPDATE("组织管理-教师管理-教师更新"),
    ORGANIZATION_TEACHER_DELETE("组织管理-教师管理-教师删除"),

    //班级管理
    ORGANIZATION_CLASS_READ("组织管理-班级管理-班级查看"),
    ORGANIZATION_CLASS_CREATE("组织管理-班级管理-班级创建"),
    ORGANIZATION_CLASS_UPDATE("组织管理-班级管理-班级更新"),
    ORGANIZATION_CLASS_DELETE("组织管理-班级管理-班级删除"),

    //学生信息
    RECORD_STUDENT_READ("档案管理-学生信息-学生信息查看"),
    RECORD_STUDENT_CREATE("档案管理-学生信息-学生信息创建"),
    RECORD_STUDENT_UPDATE("档案管理-学生信息-学生信息更新"),
    RECORD_STUDENT_DELETE("档案管理-学生信息-学生信息删除"),

    //奖惩记录
    RECORD_REWARD_PUNISHMENT_READ("档案管理-奖惩记录-奖惩记录查看"),
    RECORD_REWARD_PUNISHMENT_CREATE("档案管理-奖惩记录-奖惩记录创建"),
    RECORD_REWARD_PUNISHMENT_UPDATE("档案管理-奖惩记录-奖惩记录更新"),
    RECORD_REWARD_PUNISHMENT_DELETE("档案管理-奖惩记录-奖惩记录删除"),

    //教育经历
    RECORD_EDUCATION_EXPERIENCE_READ("档案管理-教育经历-教育经历查看"),
    RECORD_EDUCATION_EXPERIENCE_CREATE("档案管理-教育经历-教育经历创建"),
    RECORD_EDUCATION_EXPERIENCE_UPDATE("档案管理-教育经历-教育经历更新"),
    RECORD_EDUCATION_EXPERIENCE_DELETE("档案管理-教育经历-教育经历删除");

    private String description;

    Authority(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 管理员权限
     * @return
     */
    public static List<Authority> getAdminAuthority() {
        List<Authority> authorities = new ArrayList<>();
        authorities.add(Authority.SFM_SYSTEM_USER);
        authorities.add(Authority.SFM_SYSTEM_ADMIN);
        authorities.add(Authority.ORGANIZATION_COLLEGE_READ);
        authorities.add(Authority.ORGANIZATION_COLLEGE_CREATE);
        authorities.add(Authority.ORGANIZATION_COLLEGE_UPDATE);
        authorities.add(Authority.ORGANIZATION_COLLEGE_DELETE);
        authorities.add(Authority.ORGANIZATION_SPECIALTY_READ);
        authorities.add(Authority.ORGANIZATION_SPECIALTY_CREATE);
        authorities.add(Authority.ORGANIZATION_SPECIALTY_UPDATE);
        authorities.add(Authority.ORGANIZATION_SPECIALTY_DELETE);
        authorities.add(Authority.ORGANIZATION_CLASS_READ);
        authorities.add(Authority.ORGANIZATION_CLASS_CREATE);
        authorities.add(Authority.ORGANIZATION_CLASS_UPDATE);
        authorities.add(Authority.ORGANIZATION_CLASS_DELETE);
        authorities.add(Authority.ORGANIZATION_TEACHER_READ);
        authorities.add(Authority.ORGANIZATION_TEACHER_CREATE);
        authorities.add(Authority.ORGANIZATION_TEACHER_UPDATE);
        authorities.add(Authority.ORGANIZATION_TEACHER_DELETE);
        authorities.add(Authority.RECORD_STUDENT_READ);
        authorities.add(Authority.RECORD_STUDENT_CREATE);
        authorities.add(Authority.RECORD_STUDENT_UPDATE);
        authorities.add(Authority.RECORD_STUDENT_DELETE);
        authorities.add(Authority.RECORD_REWARD_PUNISHMENT_READ);
        authorities.add(Authority.RECORD_REWARD_PUNISHMENT_CREATE);
        authorities.add(Authority.RECORD_REWARD_PUNISHMENT_UPDATE);
        authorities.add(Authority.RECORD_REWARD_PUNISHMENT_DELETE);
        authorities.add(Authority.RECORD_EDUCATION_EXPERIENCE_READ);
        authorities.add(Authority.RECORD_EDUCATION_EXPERIENCE_CREATE);
        authorities.add(Authority.RECORD_EDUCATION_EXPERIENCE_UPDATE);
        authorities.add(Authority.RECORD_EDUCATION_EXPERIENCE_DELETE);
        return authorities;
    }

    /**
     * 教师权限
     * @return
     */
    public static List<Authority> getTeacherAuthority() {
        List<Authority> authorities = new ArrayList<>();
        authorities.add(Authority.SFM_SYSTEM_USER);
        authorities.add(Authority.SFM_SYSTEM_TEACHER);
        authorities.add(Authority.SFM_SYSTEM_UPDATE_INFO);
        authorities.add(Authority.SFM_SYSTEM_UPDATE_PWD);
        authorities.add(Authority.ORGANIZATION_COLLEGE_READ);
        authorities.add(Authority.ORGANIZATION_SPECIALTY_READ);
        authorities.add(Authority.ORGANIZATION_CLASS_READ);
        authorities.add(Authority.RECORD_STUDENT_READ);
        authorities.add(Authority.RECORD_REWARD_PUNISHMENT_READ);
        authorities.add(Authority.RECORD_EDUCATION_EXPERIENCE_READ);
        return authorities;
    }

    /**
     * 学生权限
     * @return
     */
    public static List<Authority> getStudentAuthority() {
        List<Authority> authorities = new ArrayList<>();
        authorities.add(Authority.SFM_SYSTEM_USER);
        authorities.add(Authority.SFM_SYSTEM_STUDENT);
        authorities.add(Authority.SFM_SYSTEM_UPDATE_INFO);
        authorities.add(Authority.SFM_SYSTEM_UPDATE_PWD);
        authorities.add(Authority.RECORD_REWARD_PUNISHMENT_READ);
        authorities.add(Authority.RECORD_EDUCATION_EXPERIENCE_READ);
        return authorities;
    }

}
