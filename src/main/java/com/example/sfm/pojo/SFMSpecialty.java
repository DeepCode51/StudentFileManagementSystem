package com.example.sfm.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.sfm.base.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 专业表 实体类
 *
 * @author lihai
 * @since 2020-10-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sfm_specialty") // 对应的数据库表名称
public class SFMSpecialty extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 专业名称
     */
    private String name;

    /**
     * 学院id
     */
    private String collegeId;

    @TableField(exist = false)
    private String collegeName;

}
