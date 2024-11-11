package com.example.sfm.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sfm.pojo.SFMSpecialty;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
*
* 专业表 Mapper 接口
*
* @author lihai
* @since 2020-10-03
*/
public interface SFMSpecialtyMapper extends BaseMapper<SFMSpecialty> {
    /**
     * 根据参数分页查询
     * @param page
     * @return
     */
    IPage<SFMSpecialty> selectPageByParams(@Param("page") Page<SFMSpecialty> page, @Param("params") SFMSpecialty params);
}
