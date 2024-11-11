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
 * 教育经历表 实体类
 *
 * @author lihai
 * @since 2020-10-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sfm_education_experience") // 对应的数据库表名称
public class SFMEducationExperience extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 学生id
     */
    private String studentId;

    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    /**
     * 在校职务
     */
    private String positionName;

    /**
     * 学校名称
     */
    private String schoolName;

    /**
     * 证明人姓名
     */
    private String certifierName;

    /**
     * 学生姓名
     */
    @TableField(exist = false)
    private String studentName;

    /**
     * 学号
     */
    @TableField(exist = false)
    private String studentNumber;

}
