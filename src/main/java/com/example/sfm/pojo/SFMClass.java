package com.example.sfm.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.sfm.base.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 班级表 实体类
 *
 * @author lihai
 * @since 2020-10-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sfm_class") // 对应的数据库表名称
public class SFMClass extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 班级编号
     */
    private String number;

    /**
     * 年级名称
     */
    private String gradeName;

    /**
     * 专业id
     */
    private String specialtyId;

    /**
     * 班级负责人id
     */
    private String teacherId;

    /**
     * 班长id
     */
    private String classPresidentId;

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
     * 专业名称
     */
    @TableField(exist = false)
    private String specialtyName;

    /**
     * 教师名称
     */
    @TableField(exist = false)
    private String teacherName;

    /**
     * 班长名称
     */
    @TableField(exist = false)
    private String classPresidentName;

}
