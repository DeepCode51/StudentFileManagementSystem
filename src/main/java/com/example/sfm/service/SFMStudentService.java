package com.example.sfm.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sfm.base.pojo.BaseEntity;
import com.example.sfm.exception.WarningException;
import com.example.sfm.mapper.SFMClassMapper;
import com.example.sfm.pojo.SFMClass;
import com.example.sfm.pojo.SFMEducationExperience;
import com.example.sfm.pojo.SFMRewardPunishment;
import com.example.sfm.pojo.SFMStudent;
import com.example.sfm.mapper.SFMStudentMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.sfm.vo.PageVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 学生信息表 服务实现类
 *
 * @author lihai
 * @since 2020-10-03
 */
@Service
public class SFMStudentService extends ServiceImpl<SFMStudentMapper, SFMStudent> {

    @Resource
    private SFMEducationExperienceService sfmEducationExperienceService;
    @Resource
    private SFMRewardPunishmentService sfmRewardPunishmentService;
    @Resource
    private SFMClassMapper sfmClassMapper;

    /**
     * 保存学生信息，保存前校验该学号是否已存在
     *
     * @param sfmStudent 学生信息
     */
    public void saveSFMStudent(SFMStudent sfmStudent) {
        String id = getIdByNumber(sfmStudent.getNumber());
        if (StringUtils.isNotBlank(sfmStudent.getId())) {
            if (StringUtils.isNotBlank(id) && !sfmStudent.getId().equals(id)) {
                throw new WarningException("该学号已存在");
            } else {
                sfmStudent.updateById();
            }
        } else {
            if (StringUtils.isNotBlank(id)) {
                throw new WarningException("该学号已存在");
            } else {
                // TODO 生成学号
                String currentNumber;
                String maxNumber = super.baseMapper.selectMaxNumber();
                if (StringUtils.isNotBlank(maxNumber)) {
                    maxNumber = maxNumber.replace("SD", "");
                    currentNumber = "SD" + String.format("%08d", Integer.parseInt(maxNumber) + 1);
                } else {
                    currentNumber = "SD" + String.format("%08d", 1);
                }
                sfmStudent.setNumber(currentNumber);

                sfmStudent.insert();
            }
        }
    }

    /**
     * 根据学号获取学生信息id
     *
     * @param number 学号
     * @return
     */
    public String getIdByNumber(String number) {
        LambdaQueryWrapper<SFMStudent> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(SFMStudent::getNumber, number);
        SFMStudent one = this.getOne(lambdaQueryWrapper);
        return one != null ? one.getId() : null;
    }

    /**
     * 根据学生信息id批量删除学生信息
     *
     * @param ids
     */
    public void deleteByIds(List<String> ids) {
        super.removeByIds(ids);

        // 删除教育经历
        LambdaQueryWrapper<SFMEducationExperience> educationExperienceQueryWrapper = Wrappers.lambdaQuery();
        educationExperienceQueryWrapper.in(SFMEducationExperience::getStudentId, ids);
        List<String> sfmEducationExperienceIds = sfmEducationExperienceService.list(educationExperienceQueryWrapper)
                .stream()
                .map(BaseEntity::getId)
                .collect(Collectors.toList());
        if (sfmEducationExperienceIds.size() > 0) {
            sfmEducationExperienceService.deleteByIds(sfmEducationExperienceIds);
        }

        // 删除奖惩记录
        LambdaQueryWrapper<SFMRewardPunishment> sfmRewardPunishmentQueryWrapper = Wrappers.lambdaQuery();
        sfmRewardPunishmentQueryWrapper.in(SFMRewardPunishment::getStudentId, ids);
        List<String> sfmRewardPunishmentIds = sfmRewardPunishmentService.list(sfmRewardPunishmentQueryWrapper)
                .stream()
                .map(BaseEntity::getId)
                .collect(Collectors.toList());
        if (sfmRewardPunishmentIds.size() > 0) {
            sfmRewardPunishmentService.deleteByIds(sfmRewardPunishmentIds);
        }
    }

    /**
     * 根据学生信息id删除学生信息
     *
     * @param id
     */
    public void deleteById(String id) {
        super.removeById(id);

        // 删除教育经历
        LambdaQueryWrapper<SFMEducationExperience> educationExperienceQueryWrapper = Wrappers.lambdaQuery();
        educationExperienceQueryWrapper.eq(SFMEducationExperience::getStudentId, id);
        List<String> sfmEducationExperienceIds = sfmEducationExperienceService.list(educationExperienceQueryWrapper)
                .stream()
                .map(BaseEntity::getId)
                .collect(Collectors.toList());
        if (sfmEducationExperienceIds.size() > 0) {
            sfmEducationExperienceService.deleteByIds(sfmEducationExperienceIds);
        }

        // 删除奖惩记录
        LambdaQueryWrapper<SFMRewardPunishment> sfmRewardPunishmentQueryWrapper = Wrappers.lambdaQuery();
        sfmRewardPunishmentQueryWrapper.eq(SFMRewardPunishment::getStudentId, id);
        List<String> sfmRewardPunishmentIds = sfmRewardPunishmentService.list(sfmRewardPunishmentQueryWrapper)
                .stream()
                .map(BaseEntity::getId)
                .collect(Collectors.toList());
        if (sfmRewardPunishmentIds.size() > 0) {
            sfmRewardPunishmentService.deleteByIds(sfmRewardPunishmentIds);
        }
    }

    /**
     * 分页查询学生信息
     *
     * @param pageVo
     * @return
     */
    public IPage<SFMStudent> selectPage(PageVo<SFMStudent> pageVo) {
        Page<SFMStudent> page = new Page<>(pageVo.getPage(), pageVo.getLimit());
        SFMStudent searchParams = pageVo.getSearchParams();
        if (searchParams == null) {
            searchParams = new SFMStudent();
        }
        return super.baseMapper.selectPageByParams(page, searchParams);
    }

    /**
     * 根据班级获取学生
     *
     * @param classId
     * @return
     */
    public List<SFMStudent> getAllByClassId(String classId) {
        return super.baseMapper.getAllByClassId(classId);
    }
}
