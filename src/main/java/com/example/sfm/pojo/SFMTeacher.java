package com.example.sfm.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.sfm.base.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 教师表 实体类
 *
 * @author lihai
 * @since 2020-10-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sfm_teacher") // 对应的数据库表名称
public class SFMTeacher extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 教师编号
     */
    private String number;

    /**
     * 教师名称
     */
    private String name;

    /**
     * 学院id
     */
    private String collegeId;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 学院名称
     */
    @TableField(exist = false)
    private String collegeName;

}
