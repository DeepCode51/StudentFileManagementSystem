package com.example.sfm.controller;

import com.example.sfm.pojo.SFMRewardPunishment;
import com.example.sfm.service.SFMRewardPunishmentService;
import com.example.sfm.util.ResultUtil;
import com.example.sfm.vo.PageVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 奖惩记录管理控制器
 */
@RestController
@RequestMapping("/api/v1/sfm/rewardPunishment")
public class SFMRewardPunishmentController {

    @Resource private SFMRewardPunishmentService sfmRewardPunishmentService;

    /**
     * 保存奖惩记录
     * @param sfmRewardPunishment 奖惩记录
     * @return
     */
    @PreAuthorize("hasAnyAuthority('RECORD_REWARD_PUNISHMENT_CREATE', 'RECORD_REWARD_PUNISHMENT_UPDATE')")
    @PostMapping("/save")
    public ResultUtil saveSFMRewardPunishment(@RequestBody SFMRewardPunishment sfmRewardPunishment) {
        sfmRewardPunishmentService.saveSFMRewardPunishment(sfmRewardPunishment);
        return ResultUtil.success();
    }

    /**
     * 根据奖惩记录id删除奖惩记录
     * @param id 奖惩记录id
     * @return
     */
    @PreAuthorize("hasAuthority('RECORD_REWARD_PUNISHMENT_DELETE')")
    @DeleteMapping("/{id}")
    public ResultUtil deleteSFMRewardPunishment(@PathVariable("id") String id) {
        sfmRewardPunishmentService.deleteById(id);
        return ResultUtil.success();
    }

    /**
     * 根据奖惩记录id批量删除奖惩记录
     * @param ids 奖惩记录id
     * @return
     */
    @PreAuthorize("hasAuthority('RECORD_REWARD_PUNISHMENT_DELETE')")
    @DeleteMapping("/batch/{ids}")
    public ResultUtil deleteSFMRewardPunishmentBatch(@PathVariable("ids") List<String> ids) {
        sfmRewardPunishmentService.deleteByIds(ids);
        return ResultUtil.success();
    }

    /**
     * 分页查询奖惩记录
     * @param pageVo 参数
     * @return
     */
    @PreAuthorize("hasAuthority('RECORD_REWARD_PUNISHMENT_READ')")
    @PostMapping("/page")
    public ResultUtil selectPage(@RequestBody PageVo<SFMRewardPunishment> pageVo) {
        return ResultUtil.success(sfmRewardPunishmentService.selectPage(pageVo));
    }

    /**
     * 查询所有奖惩记录
     * @return
     */
    @PreAuthorize("hasAuthority('RECORD_REWARD_PUNISHMENT_READ')")
    @GetMapping("/all")
    public ResultUtil all() {
        return ResultUtil.success(sfmRewardPunishmentService.list());
    }

}
