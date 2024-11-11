package com.example.sfm.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sfm.pojo.SFMTeacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
*
* 教师表 Mapper 接口
*
* @author lihai
* @since 2020-10-03
*/
public interface SFMTeacherMapper extends BaseMapper<SFMTeacher> {

    /**
     * 根据参数分页查询
     * @param page
     * @return
     */
    IPage<SFMTeacher> selectPageByParams(@Param("page") Page<SFMTeacher> page, @Param("params") SFMTeacher sfmTeacher);

    /**
     * 获取最大编号
     * @return
     */
    String selectMaxNumber();

}
