package com.example.sfm.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sfm.pojo.SFMClass;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
*
* 班级表 Mapper 接口
*
* @author lihai
* @since 2020-10-03
*/
public interface SFMClassMapper extends BaseMapper<SFMClass> {
    /**
     * 根据参数分页查询
     * @param page
     * @return
     */
    IPage<SFMClass> selectPageByParams(@Param("page") Page<SFMClass> page, @Param("params") SFMClass sfmClass);
}
