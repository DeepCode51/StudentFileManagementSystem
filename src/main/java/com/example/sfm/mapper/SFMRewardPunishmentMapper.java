package com.example.sfm.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.sfm.pojo.SFMRewardPunishment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
*
* 奖惩记录表 Mapper 接口
*
* @author lihai
* @since 2020-10-03
*/
public interface SFMRewardPunishmentMapper extends BaseMapper<SFMRewardPunishment> {
    /**
     * 根据参数分页查询
     * @param page
     * @return
     */
    IPage<SFMRewardPunishment> selectPageByParams(@Param("page") Page<SFMRewardPunishment> page, @Param("params") SFMRewardPunishment sfmRewardPunishment);
}
