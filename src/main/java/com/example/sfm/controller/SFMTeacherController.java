package com.example.sfm.controller;

import com.example.sfm.pojo.SFMTeacher;
import com.example.sfm.service.SFMTeacherService;
import com.example.sfm.util.ResultUtil;
import com.example.sfm.vo.PageVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 教师管理控制器
 */
@RestController
@RequestMapping("/api/v1/sfm/teacher")
public class SFMTeacherController {

    @Resource private SFMTeacherService sfmTeacherService;

    /**
     * 保存教师
     * @param sfmTeacher 教师
     * @return
     */
    @PreAuthorize("hasAnyAuthority('ORGANIZATION_TEACHER_CREATE', 'ORGANIZATION_TEACHER_UPDATE')")
    @PostMapping("/save")
    public ResultUtil saveSFMTeacher(@RequestBody SFMTeacher sfmTeacher) {
        sfmTeacherService.saveSFMTeacher(sfmTeacher);
        return ResultUtil.success();
    }

    /**
     * 根据教师id删除教师
     * @param id 教师id
     * @return
     */
    @PreAuthorize("hasAuthority('ORGANIZATION_TEACHER_DELETE')")
    @DeleteMapping("/{id}")
    public ResultUtil deleteSFMTeacher(@PathVariable("id") String id) {
        sfmTeacherService.deleteById(id);
        return ResultUtil.success();
    }

    /**
     * 根据教师id批量删除教师
     * @param ids 教师id
     * @return
     */
    @PreAuthorize("hasAuthority('ORGANIZATION_TEACHER_DELETE')")
    @DeleteMapping("/batch/{ids}")
    public ResultUtil deleteSFMTeacherBatch(@PathVariable("ids") List<String> ids) {
        sfmTeacherService.deleteByIds(ids);
        return ResultUtil.success();
    }

    /**
     * 分页查询教师
     * @param pageVo 参数
     * @return
     */
    @PreAuthorize("hasAuthority('ORGANIZATION_TEACHER_READ')")
    @PostMapping("/page")
    public ResultUtil selectPage(@RequestBody PageVo<SFMTeacher> pageVo) {
        return ResultUtil.success(sfmTeacherService.selectPage(pageVo));
    }

    /**
     * 查询所有教师
     * @return
     */
    @PreAuthorize("hasAuthority('ORGANIZATION_TEACHER_READ')")
    @GetMapping("/all")
    public ResultUtil all() {
        return ResultUtil.success(sfmTeacherService.list());
    }

}
