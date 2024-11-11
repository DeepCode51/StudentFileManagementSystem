package com.example.sfm.controller;

import com.example.sfm.pojo.SFMStudent;
import com.example.sfm.service.SFMStudentService;
import com.example.sfm.util.ResultUtil;
import com.example.sfm.vo.PageVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 学生信息管理控制器
 */
@RestController
@RequestMapping("/api/v1/sfm/student")
public class SFMStudentController {

    @Resource private SFMStudentService sfmStudentService;

    /**
     * 保存学生信息
     * @param sfmStudent 学生信息
     * @return
     */
    @PreAuthorize("hasAnyAuthority('RECORD_STUDENT_CREATE', 'RECORD_STUDENT_UPDATE')")
    @PostMapping("/save")
    public ResultUtil saveSFMStudent(@RequestBody SFMStudent sfmStudent) {
        sfmStudentService.saveSFMStudent(sfmStudent);
        return ResultUtil.success();
    }

    /**
     * 根据学生信息id删除学生信息
     * @param id 学生信息id
     * @return
     */
    @PreAuthorize("hasAuthority('RECORD_STUDENT_DELETE')")
    @DeleteMapping("/{id}")
    public ResultUtil deleteSFMStudent(@PathVariable("id") String id) {
        sfmStudentService.deleteById(id);
        return ResultUtil.success();
    }

    /**
     * 根据学生信息id批量删除学生信息
     * @param ids 学生信息id
     * @return
     */
    @PreAuthorize("hasAuthority('RECORD_STUDENT_DELETE')")
    @DeleteMapping("/batch/{ids}")
    public ResultUtil deleteSFMStudentBatch(@PathVariable("ids") List<String> ids) {
        sfmStudentService.deleteByIds(ids);
        return ResultUtil.success();
    }

    /**
     * 分页查询学生信息
     * @param pageVo 参数
     * @return
     */
    @PreAuthorize("hasAuthority('RECORD_STUDENT_READ')")
    @PostMapping("/page")
    public ResultUtil selectPage(@RequestBody PageVo<SFMStudent> pageVo) {
        return ResultUtil.success(sfmStudentService.selectPage(pageVo));
    }

    /**
     * 查询所有学生信息
     * @return
     */
    @PreAuthorize("hasAuthority('RECORD_STUDENT_READ')")
    @GetMapping("/all")
    public ResultUtil all() {
        return ResultUtil.success(sfmStudentService.list());
    }

    /**
     * 根据班级获取学生
     * @return
     */
    @PreAuthorize("hasAuthority('RECORD_STUDENT_READ')")
    @GetMapping("/byClass/{classId}")
    public ResultUtil allByClassNumber(@PathVariable("classId") String classId) {
        return ResultUtil.success(sfmStudentService.getAllByClassId(classId));
    }

}
