package com.example.sfm.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sfm.pojo.SFMStudent;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
*
* 学生信息表 Mapper 接口
*
* @author lihai
* @since 2020-10-03
*/
public interface SFMStudentMapper extends BaseMapper<SFMStudent> {
    /**
     * 根据参数分页查询
     * @param page
     * @return
     */
    IPage<SFMStudent> selectPageByParams(@Param("page") Page<SFMStudent> page, @Param("params") SFMStudent sfmStudent);

    /**
     * 获取最大编号
     * @return
     */
    String selectMaxNumber();

    /**
     * 根据班级获取学生
     * @param classId
     * @return
     */
    List<SFMStudent> getAllByClassId(@Param("classId") String classId);

}
