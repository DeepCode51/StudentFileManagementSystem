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
 * 奖惩记录表 实体类
 *
 * @author lihai
 * @since 2020-10-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sfm_reward_punishment") // 对应的数据库表名称
public class SFMRewardPunishment extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 学生id
     */
    private String studentId;

    /**
     * 发生时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    /**
     * 事件说明
     */
    private String incident;

    /**
     * 性质
     */
    private String nature;

    /**
     * 地点
     */
    private String site;

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
