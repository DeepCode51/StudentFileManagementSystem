package com.example.sfm.controller;

import com.example.sfm.pojo.SFMSpecialty;
import com.example.sfm.service.SFMSpecialtyService;
import com.example.sfm.util.ResultUtil;
import com.example.sfm.vo.PageVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 专业管理控制器
 */
@RestController
@RequestMapping("/api/v1/sfm/specialty")
public class SFMSpecialtyController {

    @Resource private SFMSpecialtyService sfmSpecialtyService;

    /**
     * 保存专业
     * @param sfmSpecialty 专业
     * @return
     */
    @PreAuthorize("hasAnyAuthority('ORGANIZATION_SPECIALTY_CREATE', 'ORGANIZATION_SPECIALTY_UPDATE')")
    @PostMapping("/save")
    public ResultUtil saveSFMSpecialty(@RequestBody SFMSpecialty sfmSpecialty) {
        sfmSpecialtyService.saveSFMSpecialty(sfmSpecialty);
        return ResultUtil.success();
    }

    /**
     * 根据专业id删除专业
     * @param id 专业id
     * @return
     */
    @PreAuthorize("hasAuthority('ORGANIZATION_SPECIALTY_DELETE')")
    @DeleteMapping("/{id}")
    public ResultUtil deleteSFMSpecialty(@PathVariable("id") String id) {
        sfmSpecialtyService.deleteById(id);
        return ResultUtil.success();
    }

    /**
     * 根据专业id批量删除专业
     * @param ids 专业id
     * @return
     */
    @PreAuthorize("hasAuthority('ORGANIZATION_SPECIALTY_DELETE')")
    @DeleteMapping("/batch/{ids}")
    public ResultUtil deleteSFMSpecialtyBatch(@PathVariable("ids") List<String> ids) {
        sfmSpecialtyService.deleteByIds(ids);
        return ResultUtil.success();
    }

    /**
     * 分页查询专业
     * @param pageVo 参数
     * @return
     */
    @PreAuthorize("hasAuthority('ORGANIZATION_SPECIALTY_READ')")
    @PostMapping("/page")
    public ResultUtil selectPage(@RequestBody PageVo<SFMSpecialty> pageVo) {
        return ResultUtil.success(sfmSpecialtyService.selectPage(pageVo));
    }

    /**
     * 查询所有专业
     * @return
     */
    @PreAuthorize("hasAuthority('ORGANIZATION_SPECIALTY_READ')")
    @GetMapping("/all")
    public ResultUtil all() {
        return ResultUtil.success(sfmSpecialtyService.list());
    }

}
