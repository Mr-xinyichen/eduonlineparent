package com.eduonline.eduucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eduonline.eduucenter.entity.UcenterMember;
import com.eduonline.eduucenter.mapper.UcenterMemberMapper;
import com.eduonline.eduucenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author Chenxinyi
 * @since 2020-03-04
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {


    /**
     * //根据openid判断数据库表是否已经存在当前扫描微信用户
     * @param openid
     * @return
     */
    @Override
    public UcenterMember existWxUser(String openid) {
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid",openid);
        return baseMapper.selectOne(queryWrapper);
    }

    /**
     * 统计某天注册人数
     * @param day
     * @return
     */
    @Override
    public Integer getRegistNumByDay(String day) {
        return baseMapper.getRegistNumByDay(day);
    }
}
