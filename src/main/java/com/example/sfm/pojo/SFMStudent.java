package com.example.sfm.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.sfm.base.pojo.BaseEntity;

import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 学生信息表 实体类
 *
 * @author lihai
 * @since 2020-10-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sfm_student") // 对应的数据库表名称
public class SFMStudent extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 学号
     */
    private String number;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别（男1，女2）
     */
    private String gender;

    /**
     * 民族
     */
    private String nation;

    /**
     * 出生日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthdate;

    /**
     * 籍贯
     */
    private String nativePlace;

    /**
     * 身份证号码
     */
    private String idCard;

    /**
     * 入校日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date enrolledDate;

    /**
     * 班级Id
     */
    private String classId;

    /**
     * 免冠照片
     */
    private String bareheadedPhoto;

    /**
     * 档案编号
     */
    private String fileNumber;

    /**
     * 学籍状况：1在读、2休学、3退学、4转出
     */
    private String status;

    /**
     * 家庭住址
     */
    private String homeAddress;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 班级编号
     */
    @TableField(exist = false)
    private String classNumber;

    /**
     * 学院id
     */
    @TableField(exist = false)
    private String collegeId;

    /**
     * 学院名称
     */
    @TableField(exist = false)
    private String collegeName;

    /**
     * 专业id
     */
    @TableField(exist = false)
    private String specialtyId;

    /**
     * 专业名称
     */
    @TableField(exist = false)
    private String specialtyName;

    /**
     * 教师名称
     */
    @TableField(exist = false)
    private String teacherName;


}
