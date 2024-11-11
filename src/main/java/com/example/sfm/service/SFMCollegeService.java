package com.example.sfm.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sfm.base.pojo.BaseEntity;
import com.example.sfm.exception.WarningException;
import com.example.sfm.pojo.SFMCollege;
import com.example.sfm.mapper.SFMCollegeMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.sfm.pojo.SFMSpecialty;
import com.example.sfm.pojo.SFMTeacher;
import com.example.sfm.vo.PageVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
*
* 学院表 服务实现类
*
*
* @author lihai
* @since 2020-10-03
*/
@Service
public class SFMCollegeService extends ServiceImpl<SFMCollegeMapper, SFMCollege>  {

    @Resource private SFMSpecialtyService sfmSpecialtyService;
    @Resource private SFMTeacherService sfmTeacherService;

    /**
     * 保存学院，保存前校验学院名称是否已存在
     * @param sfmCollege
     */
    public void saveSFMCollege(SFMCollege sfmCollege) {
        String id = getIdByName(sfmCollege.getName());
        if (StringUtils.isNotBlank(sfmCollege.getId())) {
            if (StringUtils.isNotBlank(id) && !sfmCollege.getId().equals(id)) {
                throw new WarningException("学院名称已存在");
            } else {
                sfmCollege.updateById();
            }
        } else {
            if (StringUtils.isNotBlank(id)) {
                throw new WarningException("学院名称已存在");
            } else {
                sfmCollege.insert();
            }
        }
    }

    /**
     * 根据学院名称获取学院id
     * @param name 学院名称
     * @return
     */
    public String getIdByName(String name) {
        LambdaQueryWrapper<SFMCollege> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(SFMCollege::getName, name);
        SFMCollege one = this.getOne(lambdaQueryWrapper);
        return one != null ? one.getId() : null;
    }

    /**
     * 根据学院id批量删除学院
     * @param ids
     */
    public void deleteByIds(List<String> ids) {
        super.removeByIds(ids);
        LambdaQueryWrapper<SFMSpecialty> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.in(SFMSpecialty::getCollegeId, ids);
        List<String> sfmSpecialtyIds = sfmSpecialtyService.list(lambdaQueryWrapper)
                .stream()
                .map(BaseEntity::getId)
                .collect(Collectors.toList());
        if (sfmSpecialtyIds.size() > 0) {
            sfmSpecialtyService.deleteByIds(sfmSpecialtyIds);
        }

        LambdaQueryWrapper<SFMTeacher> teacherQueryWrapper = Wrappers.lambdaQuery();
        teacherQueryWrapper.in(SFMTeacher::getCollegeId, ids);
        List<String> sfmTeacherIds = sfmTeacherService.list(teacherQueryWrapper)
                .stream()
                .map(BaseEntity::getId)
                .collect(Collectors.toList());
        if (sfmTeacherIds.size() > 0) {
            sfmTeacherService.deleteByIds(sfmTeacherIds);
        }
    }

    /**
     * 根据学院id删除学院
     * @param id
     */
    public void deleteById(String id) {
        super.removeById(id);
        LambdaQueryWrapper<SFMSpecialty> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(SFMSpecialty::getCollegeId, id);
        List<String> sfmSpecialtyIds = sfmSpecialtyService.list(lambdaQueryWrapper)
                .stream()
                .map(BaseEntity::getId)
                .collect(Collectors.toList());
        if (sfmSpecialtyIds.size() > 0) {
            sfmSpecialtyService.deleteByIds(sfmSpecialtyIds);
        }

        LambdaQueryWrapper<SFMTeacher> teacherQueryWrapper = Wrappers.lambdaQuery();
        teacherQueryWrapper.eq(SFMTeacher::getCollegeId, id);
        List<String> sfmTeacherIds = sfmTeacherService.list(teacherQueryWrapper)
                .stream()
                .map(BaseEntity::getId)
                .collect(Collectors.toList());
        if (sfmTeacherIds.size() > 0) {
            sfmTeacherService.deleteByIds(sfmTeacherIds);
        }
    }

    /**
     * 分页查询学院
     * @param pageVo
     * @return
     */
    public IPage<SFMCollege> selectPage(PageVo<SFMCollege> pageVo) {
        Page<SFMCollege> page = new Page<>(pageVo.getPage() - 1, pageVo.getLimit());
        LambdaQueryWrapper<SFMCollege> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.orderByDesc(BaseEntity::getCreateDate);
        if (pageVo.getSearchParams() != null) {
            SFMCollege sfmCollege = pageVo.getSearchParams();
            if (StringUtils.isNotBlank(sfmCollege.getId())) {
                lambdaQueryWrapper.eq(SFMCollege::getId, sfmCollege.getId());
            }
        }
        return super.baseMapper.selectPage(page, lambdaQueryWrapper);
    }
}
