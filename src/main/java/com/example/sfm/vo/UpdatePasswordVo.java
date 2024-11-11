package com.example.sfm.vo;

import lombok.Data;

/**
 * 接收修改密码的信息
 */
@Data
public class UpdatePasswordVo {
    private String oldPassword;
    private String newPassword;
}
