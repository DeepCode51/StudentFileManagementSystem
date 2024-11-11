package com.example.sfm.controller;

import com.example.sfm.pojo.SFMClass;
import com.example.sfm.service.SFMClassService;
import com.example.sfm.util.ResultUtil;
import com.example.sfm.vo.PageVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 班级管理控制器
 */
@RestController
@RequestMapping("/api/v1/sfm/class")
public class SFMClassController {

    @Resource private SFMClassService sfmClassService;

    /**
     * 保存班级
     * @param sfmClass 班级
     * @return
     */
    @PreAuthorize("hasAnyAuthority('ORGANIZATION_CLASS_CREATE', 'ORGANIZATION_CLASS_UPDATE')")
    @PostMapping("/save")
    public ResultUtil saveSFMClass(@RequestBody SFMClass sfmClass) {
        sfmClassService.saveSFMClass(sfmClass);
        return ResultUtil.success();
    }

    /**
     * 根据班级id删除班级
     * @param id 班级id
     * @return
     */
    @PreAuthorize("hasAuthority('ORGANIZATION_CLASS_DELETE')")
    @DeleteMapping("/{id}")
    public ResultUtil deleteSFMClass(@PathVariable("id") String id) {
        sfmClassService.deleteById(id);
        return ResultUtil.success();
    }

    /**
     * 根据班级id批量删除班级
     * @param ids 班级id
     * @return
     */
    @PreAuthorize("hasAuthority('ORGANIZATION_CLASS_DELETE')")
    @DeleteMapping("/batch/{ids}")
    public ResultUtil deleteSFMClassBatch(@PathVariable("ids") List<String> ids) {
        sfmClassService.deleteByIds(ids);
        return ResultUtil.success();
    }

    /**
     * 分页查询班级
     * @param pageVo 参数
     * @return
     */
    @PreAuthorize("hasAuthority('ORGANIZATION_CLASS_READ')")
    @PostMapping("/page")
    public ResultUtil selectPage(@RequestBody PageVo<SFMClass> pageVo) {
        return ResultUtil.success(sfmClassService.selectPage(pageVo));
    }

    /**
     * 查询所有班级
     * @return
     */
    @PreAuthorize("hasAuthority('ORGANIZATION_CLASS_READ')")
    @GetMapping("/all")
    public ResultUtil all() {
        return ResultUtil.success(sfmClassService.list());
    }

}
