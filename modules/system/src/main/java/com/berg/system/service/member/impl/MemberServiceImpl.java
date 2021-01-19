package com.berg.system.service.member.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.berg.dao.page.PageInfo;
import com.berg.dao.system.mb.entity.MemberTbl;
import com.berg.dao.system.mb.entity.MpBindTbl;
import com.berg.dao.system.mb.service.MemberTblDao;
import com.berg.dao.system.mb.service.MpBindTblDao;
import com.berg.system.service.member.MemberService;
import com.berg.vo.member.MemberVo;
import com.berg.vo.member.MpBindVo;
import com.berg.vo.member.in.GetMemberPageInVo;
import com.berg.vo.member.in.GetMpBindPageInVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberTblDao memberTblDao;
    @Autowired
    MpBindTblDao mpBindTblDao;

    /**
     * 获取会员分页列表
     * @param input
     * @return
     */
    @Override
    public PageInfo<MemberVo> getMemberPage(GetMemberPageInVo input){
        return memberTblDao.page(input,()->{
            LambdaQueryWrapper query = new LambdaQueryWrapper<MemberTbl>().like(StringUtils.isNotBlank(input.getPhone()),MemberTbl::getPhone,input.getPhone());
            return memberTblDao.list(query,MemberVo.class);
        });
    }

    /**
     * 获取会员微信小程序绑定分页列表
     * @param input
     * @return
     */
    @Override
    public PageInfo<MpBindVo> getMpBindPage(GetMpBindPageInVo input){
        return  mpBindTblDao.page(input,()->{
            LambdaQueryWrapper query = new LambdaQueryWrapper<MpBindTbl>()
                    .eq(StringUtils.isNotBlank(input.getAppId()),MpBindTbl::getAppId,input.getAppId())
                    .like(StringUtils.isNotBlank(input.getNickName()),MpBindTbl::getNickname,input.getNickName());
            return mpBindTblDao.list(query,MpBindVo.class);
        });
    }
}
