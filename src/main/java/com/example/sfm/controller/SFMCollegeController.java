package com.example.sfm.controller;

import com.example.sfm.pojo.SFMCollege;
import com.example.sfm.service.SFMCollegeService;
import com.example.sfm.util.ResultUtil;
import com.example.sfm.vo.PageVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 学院管理控制器
 */
@RestController
@RequestMapping("/api/v1/sfm/college")
public class SFMCollegeController {

    @Resource private SFMCollegeService sfmCollegeService;

    /**
     * 保存学院
     * @param sfmCollege 学院
     * @return
     */
    @PreAuthorize("hasAnyAuthority('ORGANIZATION_COLLEGE_CREATE', 'ORGANIZATION_COLLEGE_UPDATE')")
    @PostMapping("/save")
    public ResultUtil saveSFMCollege(@RequestBody SFMCollege sfmCollege) {
        sfmCollegeService.saveSFMCollege(sfmCollege);
        return ResultUtil.success();
    }

    /**
     * 根据学院id删除学院
     * @param id 学院id
     * @return
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ORGANIZATION_COLLEGE_DELETE')")
    public ResultUtil deleteSFMCollege(@PathVariable("id") String id) {
        sfmCollegeService.deleteById(id);
        return ResultUtil.success();
    }

    /**
     * 根据学院id批量删除学院
     * @param ids 学院id
     * @return
     */
    @PreAuthorize("hasAuthority('ORGANIZATION_COLLEGE_DELETE')")
    @DeleteMapping("/batch/{ids}")
    public ResultUtil deleteSFMCollegeBatch(@PathVariable("ids") List<String> ids) {
        sfmCollegeService.deleteByIds(ids);
        return ResultUtil.success();
    }

    /**
     * 分页查询学院
     * @param pageVo 参数
     * @return
     */
    @PreAuthorize("hasAuthority('ORGANIZATION_COLLEGE_READ')")
    @PostMapping("/page")
    public ResultUtil selectPage(@RequestBody PageVo<SFMCollege> pageVo) {
        return ResultUtil.success(sfmCollegeService.selectPage(pageVo));
    }

    /**
     * 查询所有学院
     * @return
     */
    @PreAuthorize("hasAuthority('ORGANIZATION_COLLEGE_READ')")
    @GetMapping("/all")
    public ResultUtil all() {
        return ResultUtil.success(sfmCollegeService.list());
    }

}
