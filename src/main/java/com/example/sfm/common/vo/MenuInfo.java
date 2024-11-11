package com.example.sfm.common.vo;

import lombok.Data;

import java.util.List;

/**
 * @author lihai
 * Create Date: 2020-10-05
 */
@Data
public class MenuInfo {
    private String title;
    private String icon;
    private String href;
    private String target;
    private List<MenuInfo> child;
}
