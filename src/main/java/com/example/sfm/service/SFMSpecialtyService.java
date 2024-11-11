package com.example.sfm.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sfm.base.pojo.BaseEntity;
import com.example.sfm.exception.WarningException;
import com.example.sfm.pojo.SFMClass;
import com.example.sfm.pojo.SFMSpecialty;
import com.example.sfm.mapper.SFMSpecialtyMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.sfm.vo.PageVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
*
* 专业表 服务实现类
*
*
* @author lihai
* @since 2020-10-03
*/
@Service
public class SFMSpecialtyService extends ServiceImpl<SFMSpecialtyMapper, SFMSpecialty>  {

    @Resource SFMClassService sfmClassService;

    /**
     * 保存专业，保存前校验专业名称是否已存在
     * @param sfmSpecialty 专业
     */
    public void saveSFMSpecialty(SFMSpecialty sfmSpecialty) {
        String id = getIdByName(sfmSpecialty.getName());
        if (StringUtils.isNotBlank(sfmSpecialty.getId())) {
            if (StringUtils.isNotBlank(id) && !sfmSpecialty.getId().equals(id)) {
                throw new WarningException("专业名称已存在");
            } else {
                sfmSpecialty.updateById();
            }
        } else {
            if (StringUtils.isNotBlank(id)) {
                throw new WarningException("专业名称已存在");
            } else {
                sfmSpecialty.insert();
            }
        }
    }

    /**
     * 根据专业名称获取专业id
     * @param name 专业名称
     * @return
     */
    public String getIdByName(String name) {
        LambdaQueryWrapper<SFMSpecialty> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(SFMSpecialty::getName, name);
        SFMSpecialty one = this.getOne(lambdaQueryWrapper);
        return one != null ? one.getId() : null;
    }

    /**
     * 根据专业id批量删除专业
     * @param ids
     */
    public void deleteByIds(List<String> ids) {
        super.removeByIds(ids);
        LambdaQueryWrapper<SFMClass> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.in(SFMClass::getSpecialtyId, ids);
        List<String> sfmClassIds = sfmClassService.list(lambdaQueryWrapper)
                .stream()
                .map(BaseEntity::getId)
                .collect(Collectors.toList());
        if (sfmClassIds.size() > 0) {
            sfmClassService.deleteByIds(sfmClassIds);
        }
    }

    /**
     * 根据专业id删除专业
     * @param id
     */
    public void deleteById(String id) {
        super.removeById(id);
        LambdaQueryWrapper<SFMClass> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(SFMClass::getSpecialtyId, id);
        List<String> sfmClassIds = sfmClassService.list(lambdaQueryWrapper)
                .stream()
                .map(BaseEntity::getId)
                .collect(Collectors.toList());
        if (sfmClassIds.size() > 0) {
            sfmClassService.deleteByIds(sfmClassIds);
        }
    }

    /**
     * 分页查询专业
     * @param pageVo
     * @return
     */
    public IPage<SFMSpecialty> selectPage(PageVo<SFMSpecialty> pageVo) {
        Page<SFMSpecialty> page = new Page<>(pageVo.getPage(), pageVo.getLimit());
        SFMSpecialty searchParams = pageVo.getSearchParams();
        if (searchParams == null) {
            searchParams = new SFMSpecialty();
        }
        return super.baseMapper.selectPageByParams(page, searchParams);
    }

}
