package com.example.sfm.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sfm.common.service.UserService;
import com.example.sfm.exception.WarningException;
import com.example.sfm.mapper.SFMStudentMapper;
import com.example.sfm.pojo.SFMEducationExperience;
import com.example.sfm.mapper.SFMEducationExperienceMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.sfm.pojo.SFMStudent;
import com.example.sfm.vo.PageVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
*
* 教育经历表 服务实现类
*
*
* @author lihai
* @since 2020-10-03
*/
@Service
public class SFMEducationExperienceService extends ServiceImpl<SFMEducationExperienceMapper, SFMEducationExperience>  {

    @Resource private UserService userService;
    @Resource private SFMStudentMapper sfmStudentMapper;

    /**
     * 保存教育经历
     * @param sfmEducationExperience 教育经历
     */
    public void saveSFMEducationExperience(SFMEducationExperience sfmEducationExperience) {
        LambdaQueryWrapper<SFMStudent> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(SFMStudent::getNumber, sfmEducationExperience.getStudentNumber());
        SFMStudent sfmStudent = sfmStudentMapper.selectOne(lambdaQueryWrapper);
        if (sfmStudent == null) {
            throw new WarningException("学号不存在");
        } else {
            sfmEducationExperience.setStudentId(sfmStudent.getId());
            if (StringUtils.isNotBlank(sfmEducationExperience.getId())) {
                sfmEducationExperience.updateById();
            } else {
                sfmEducationExperience.insert();
            }
        }
    }

    /**
     * 根据教育经历id批量删除教育经历
     * @param ids
     */
    public void deleteByIds(List<String> ids) {
        super.removeByIds(ids);
    }

    /**
     * 根据教育经历id删除教育经历
     * @param id
     */
    public void deleteById(String id) {
        super.removeById(id);
    }

    /**
     * 分页查询教育经历
     * @param pageVo
     * @return
     */
    public IPage<SFMEducationExperience> selectPage(PageVo<SFMEducationExperience> pageVo) {
        Page<SFMEducationExperience> page = new Page<>(pageVo.getPage(), pageVo.getLimit());
        SFMEducationExperience searchParams = pageVo.getSearchParams();
        if (searchParams == null) {
            searchParams = new SFMEducationExperience();
        }

        // 获取当前登录的用户
        Authentication authentication = UserService.getAuthentication();
        if (authentication == null) return null;
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        // 判断当前登录的用户是否为学生，是则只能查看学生本人的教育经历
        if (authorities.contains(new SimpleGrantedAuthority("SFM_SYSTEM_STUDENT"))) {
            SFMStudent currentLoginUser = userService.getCurrentLoginUser(new SFMStudent());
            searchParams.setStudentId(currentLoginUser.getId());
        }

        return super.baseMapper.selectPageByParams(page, searchParams);
    }

}
