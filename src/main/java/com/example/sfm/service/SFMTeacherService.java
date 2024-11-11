package com.example.sfm.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sfm.base.pojo.BaseEntity;
import com.example.sfm.exception.WarningException;
import com.example.sfm.pojo.SFMTeacher;
import com.example.sfm.mapper.SFMTeacherMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.sfm.vo.PageVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
*
* 教师表 服务实现类
*
* @author lihai
* @since 2020-10-03
*/
@Service
public class SFMTeacherService extends ServiceImpl<SFMTeacherMapper, SFMTeacher>  {

    /**
     * 保存教师，保存前校验教师编号是否已存在
     * @param sfmTeacher 教师
     */
    public void saveSFMTeacher(SFMTeacher sfmTeacher) {
        String id = getIdByNumber(sfmTeacher.getNumber());
        if (StringUtils.isNotBlank(sfmTeacher.getId())) {
            if (StringUtils.isNotBlank(id) && !sfmTeacher.getId().equals(id)) {
                throw new WarningException("教师编号已存在");
            } else {
                sfmTeacher.updateById();
            }
        } else {
            if (StringUtils.isNotBlank(id)) {
                throw new WarningException("教师编号已存在");
            } else {
                // TODO 生成教师编号
                String currentNumber;
                String maxNumber = super.baseMapper.selectMaxNumber();
                if (StringUtils.isNotBlank(maxNumber)) {
                    maxNumber = maxNumber.replace("TC", "");
                    currentNumber = "TC" + String.format("%04d", Integer.parseInt(maxNumber) + 1);
                } else {
                    currentNumber = "TC" + String.format("%04d", 1);
                }

                sfmTeacher.setNumber(currentNumber);
                sfmTeacher.insert();
            }
        }
    }

    /**
     * 根据教师编号获取教师id
     * @param number 教师编号
     * @return
     */
    public String getIdByNumber(String number) {
        LambdaQueryWrapper<SFMTeacher> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(SFMTeacher::getNumber, number);
        SFMTeacher one = this.getOne(lambdaQueryWrapper);
        return one != null ? one.getId() : null;
    }

    /**
     * 根据教师id批量删除教师
     * @param ids
     */
    public void deleteByIds(List<String> ids) {
        super.removeByIds(ids);
    }

    /**
     * 根据教师id删除教师
     * @param id
     */
    public void deleteById(String id) {
        super.removeById(id);
    }

    /**
     * 分页查询教师
     * @param pageVo
     * @return
     */
    public IPage<SFMTeacher> selectPage(PageVo<SFMTeacher> pageVo) {
        Page<SFMTeacher> page = new Page<>(pageVo.getPage(), pageVo.getLimit());
        SFMTeacher searchParams = pageVo.getSearchParams();
        if (searchParams == null) {
            searchParams = new SFMTeacher();
        }

        return super.baseMapper.selectPageByParams(page, searchParams);
    }
}
