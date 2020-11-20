package com.berg.mp.service.mp.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.berg.dao.system.mb.entity.MemberTbl;
import com.berg.dao.system.mb.entity.MpBindTbl;
import com.berg.dao.system.mb.service.MemberTblDao;
import com.berg.dao.system.mb.service.MpBindTblDao;
import com.berg.vo.mp.out.MpOAuthUserInfoOutVo;
import com.berg.vo.mp.out.MpUserInfoOutVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
public class UserAsyncTask {

    @Autowired
    MpBindTblDao mpBindTblDao;

    /**
     * 更新用户信息
     * @param appId
     * @param openId
     * @param input
     */
    @Async
    public void updateMaBindByUserInfo(String appId, String openId, MpUserInfoOutVo input){
        LambdaQueryWrapper query = new LambdaQueryWrapper<MpBindTbl>()
                .eq(MpBindTbl::getAppId, appId)
                .eq(MpBindTbl::getOpenId, openId);
        MpBindTbl mpBindTbl = new MpBindTbl();
        BeanUtils.copyProperties(input,mpBindTbl);
        if(input.getPrivileges().length>0){
            mpBindTbl.setPrivileges(StringUtils.join(Arrays.asList(input.getPrivileges()),","));
        }
        if(input.getTagIds().length>0){
            mpBindTbl.setTagIds(StringUtils.join(Arrays.asList(input.getTagIds()),","));
        }
        mpBindTbl.setModifyTime(LocalDateTime.now());
        mpBindTblDao.update(mpBindTbl, query);
    }

    /**
     * 更新通过网页授权获取用户信息
     * @param appId
     * @param openId
     * @param input
     */
    @Async
    public void updateMaBindByOAuthUserInfo(String appId, String openId, MpOAuthUserInfoOutVo input){
        LambdaQueryWrapper query = new LambdaQueryWrapper<MpBindTbl>()
                .eq(MpBindTbl::getAppId, appId)
                .eq(MpBindTbl::getOpenId, openId);
        MpBindTbl mpBindTbl = new MpBindTbl();
        BeanUtils.copyProperties(input,mpBindTbl);
        if(input.getPrivileges().length>0){
            mpBindTbl.setPrivileges(StringUtils.join(Arrays.asList(input.getPrivileges()),","));
        }
        mpBindTbl.setModifyTime(LocalDateTime.now());
        mpBindTblDao.update(mpBindTbl, query);
    }
}
