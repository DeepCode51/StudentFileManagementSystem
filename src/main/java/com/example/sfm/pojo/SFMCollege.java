package com.example.sfm.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.sfm.base.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 学院表 实体类
 *
 * @author lihai
 * @since 2020-10-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sfm_college") // 对应的数据库表名称
public class SFMCollege extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 学院名称
     */
    private String name;


}
