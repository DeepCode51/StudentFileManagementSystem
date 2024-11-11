package com.example.sfm.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sfm.pojo.SFMEducationExperience;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
*
* 教育经历表 Mapper 接口
*
* @author lihai
* @since 2020-10-03
*/
public interface SFMEducationExperienceMapper extends BaseMapper<SFMEducationExperience> {
    /**
     * 根据参数分页查询
     * @param page
     * @return
     */
    IPage<SFMEducationExperience> selectPageByParams(@Param("page") Page<SFMEducationExperience> page, @Param("params") SFMEducationExperience sfmEducationExperience);
}
