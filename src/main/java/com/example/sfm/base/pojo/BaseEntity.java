package com.example.sfm.base.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class BaseEntity extends Model<BaseEntity> implements Serializable {

    /**
     * ID主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 创建时间
     */
    @TableField(value="create_date",fill= FieldFill.INSERT)
    private LocalDateTime createDate;

    /**
     * 创建人
     */
    @TableField(value="create_by",fill=FieldFill.INSERT)
    private String createBy;

    /**
     * 更新时间
     */
    @TableField(value="update_date",fill=FieldFill.UPDATE)
    private LocalDateTime updateDate;

    /**
     * 更新人
     */
    @TableField(value="update_by",fill=FieldFill.UPDATE)
    private String updateBy;

    /**
     * 删除Flag 0 - 未删除 1 - 已删除
     */
    @TableLogic
    @TableField(value="del_flag",fill=FieldFill.INSERT)
    private String delFlag;

}
