package com.example.sfm.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sfm.common.service.UserService;
import com.example.sfm.exception.WarningException;
import com.example.sfm.mapper.SFMStudentMapper;
import com.example.sfm.pojo.SFMEducationExperience;
import com.example.sfm.pojo.SFMRewardPunishment;
import com.example.sfm.mapper.SFMRewardPunishmentMapper;
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
 * 奖惩记录表 服务实现类
 *
 * @author lihai
 * @since 2020-10-03
 */
@Service
public class SFMRewardPunishmentService extends ServiceImpl<SFMRewardPunishmentMapper, SFMRewardPunishment> {

    @Resource
    private UserService userService;
    @Resource
    private SFMStudentMapper sfmStudentMapper;

    /**
     * 保存奖惩记录
     *
     * @param sfmRewardPunishment 奖惩记录
     */
    public void saveSFMRewardPunishment(SFMRewardPunishment sfmRewardPunishment) {
        LambdaQueryWrapper<SFMStudent> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(SFMStudent::getNumber, sfmRewardPunishment.getStudentNumber());
        SFMStudent sfmStudent = sfmStudentMapper.selectOne(lambdaQueryWrapper);
        if (sfmStudent == null) {
            throw new WarningException("学号不存在");
        } else {
            sfmRewardPunishment.setStudentId(sfmStudent.getId());
            if (StringUtils.isNotBlank(sfmRewardPunishment.getId())) {
                sfmRewardPunishment.updateById();
            } else {
                sfmRewardPunishment.insert();
            }
        }

    }

    /**
     * 根据奖惩记录id批量删除奖惩记录
     *
     * @param ids
     */
    public void deleteByIds(List<String> ids) {
        super.removeByIds(ids);
    }

    /**
     * 根据奖惩记录id删除奖惩记录
     *
     * @param id
     */
    public void deleteById(String id) {
        super.removeById(id);
    }

    /**
     * 分页查询奖惩记录
     *
     * @param pageVo
     * @return
     */
    public IPage<SFMRewardPunishment> selectPage(PageVo<SFMRewardPunishment> pageVo) {
        Page<SFMRewardPunishment> page = new Page<>(pageVo.getPage(), pageVo.getLimit());
        SFMRewardPunishment searchParams = pageVo.getSearchParams();
        if (searchParams == null) {
            searchParams = new SFMRewardPunishment();
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
