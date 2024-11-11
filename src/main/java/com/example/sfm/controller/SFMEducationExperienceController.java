package com.example.sfm.controller;

import com.example.sfm.pojo.SFMEducationExperience;
import com.example.sfm.service.SFMEducationExperienceService;
import com.example.sfm.util.ResultUtil;
import com.example.sfm.vo.PageVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 教育经历管理控制器
 */
@RestController
@RequestMapping("/api/v1/sfm/educationExperience")
public class SFMEducationExperienceController {

    @Resource private SFMEducationExperienceService sfmEducationExperienceService;

    /**
     * 保存教育经历
     * @param sfmEducationExperience 教育经历
     * @return
     */
    @PreAuthorize("hasAnyAuthority('RECORD_EDUCATION_EXPERIENCE_CREATE', 'RECORD_EDUCATION_EXPERIENCE_UPDATE')")
    @PostMapping("/save")
    public ResultUtil saveSFMEducationExperience(@RequestBody SFMEducationExperience sfmEducationExperience) {
        sfmEducationExperienceService.saveSFMEducationExperience(sfmEducationExperience);
        return ResultUtil.success();
    }

    /**
     * 根据教育经历id删除教育经历
     * @param id 教育经历id
     * @return
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('RECORD_EDUCATION_EXPERIENCE_DELETE')")
    public ResultUtil deleteSFMEducationExperience(@PathVariable("id") String id) {
        sfmEducationExperienceService.deleteById(id);
        return ResultUtil.success();
    }

    /**
     * 根据教育经历id批量删除教育经历
     * @param ids 教育经历id
     * @return
     */
    @PreAuthorize("hasAuthority('RECORD_EDUCATION_EXPERIENCE_DELETE')")
    @DeleteMapping("/batch/{ids}")
    public ResultUtil deleteSFMEducationExperienceBatch(@PathVariable("ids") List<String> ids) {
        sfmEducationExperienceService.deleteByIds(ids);
        return ResultUtil.success();
    }

    /**
     * 分页查询教育经历
     * @param pageVo 参数
     * @return
     */
    @PreAuthorize("hasAuthority('RECORD_EDUCATION_EXPERIENCE_READ')")
    @PostMapping("/page")
    public ResultUtil selectPage(@RequestBody PageVo<SFMEducationExperience> pageVo) {
        return ResultUtil.success(sfmEducationExperienceService.selectPage(pageVo));
    }

    /**
     * 查询所有教育经历
     * @return
     */
    @PreAuthorize("hasAuthority('RECORD_EDUCATION_EXPERIENCE_READ')")
    @GetMapping("/all")
    public ResultUtil all() {
        return ResultUtil.success(sfmEducationExperienceService.list());
    }

}
