package com.eduonline.eduucenter.service;

import com.eduonline.eduucenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author Chenxinyi
 * @since 2020-03-04
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    //根据openid判断数据库表是否已经存在当前扫描微信用户
    UcenterMember existWxUser(String openid) ;

    //统计某天注册人数
    Integer getRegistNumByDay(String day);
}
