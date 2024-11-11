package com.example.sfm.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sfm.exception.WarningException;
import com.example.sfm.pojo.SFMClass;
import com.example.sfm.mapper.SFMClassMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.sfm.pojo.SFMSpecialty;
import com.example.sfm.vo.PageVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
*
* 班级表 服务实现类
*
*
* @author lihai
* @since 2020-10-03
*/
@Service
public class SFMClassService extends ServiceImpl<SFMClassMapper, SFMClass>  {

    /**
     * 保存班级，保存前校验该专业班级编号是否已存在
     * @param sfmClass 班级
     */
    public void saveSFMClass(SFMClass sfmClass) {
        String id = getIdByNumberAndSpecialtyId(sfmClass.getNumber(), sfmClass.getSpecialtyId());
        if (StringUtils.isNotBlank(sfmClass.getId())) {
            if (StringUtils.isNotBlank(id) && !sfmClass.getId().equals(id)) {
                throw new WarningException("该专业班级编号：" + sfmClass.getNumber() + "已存在");
            } else {
                sfmClass.updateById();
            }
        } else {
            if (StringUtils.isNotBlank(id)) {
                throw new WarningException("该专业班级编号：" + sfmClass.getNumber() + "已存在");
            } else {
                sfmClass.insert();
            }
        }
    }

    /**
     * 根据班级编号、专业id获取班级id
     * @param number 班级编号
     * @param specialtyId 专业id
     * @return
     */
    public String getIdByNumberAndSpecialtyId(String number, String specialtyId) {
        LambdaQueryWrapper<SFMClass> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(SFMClass::getNumber, number);
        lambdaQueryWrapper.eq(SFMClass::getSpecialtyId, specialtyId);
        SFMClass one = this.getOne(lambdaQueryWrapper);
        return one != null ? one.getId() : null;
    }

    /**
     * 根据班级id批量删除班级
     * @param ids
     */
    public void deleteByIds(List<String> ids) {
        super.removeByIds(ids);
    }

    /**
     * 根据班级id删除班级
     * @param id
     */
    public void deleteById(String id) {
        super.removeById(id);
    }

    /**
     * 分页查询班级
     * @param pageVo
     * @return
     */
    public IPage<SFMClass> selectPage(PageVo<SFMClass> pageVo) {
        Page<SFMClass> page = new Page<>(pageVo.getPage(), pageVo.getLimit());
        SFMClass searchParams = pageVo.getSearchParams();
        if (searchParams == null) {
            searchParams = new SFMClass();
        }
        return super.baseMapper.selectPageByParams(page, searchParams);
    }
}
