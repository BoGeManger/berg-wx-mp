package com.berg.mp.service.mp.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.berg.dao.system.mb.entity.MpBindTbl;
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

    /**
     * 新增或更新关注用户信息
     * @param appId
     * @param openId
     * @param subscribeScene
     */
    @Async
    public void addOrUpdateMaBindBySubscribe(String appId,String openId,String subscribeScene){
        LocalDateTime now = LocalDateTime.now();
        LambdaQueryWrapper query = new LambdaQueryWrapper<MpBindTbl>().eq(MpBindTbl::getAppId,appId).eq(MpBindTbl::getOpenId,openId);
        MpBindTbl mpBindTbl = mpBindTblDao.getOneLimit(query);
        if(mpBindTbl==null){
            mpBindTbl.setAppId(appId);
            mpBindTbl.setOpenId(openId);
            mpBindTbl.setCreateTime(now);
        }
        mpBindTbl.setSubscribe(1);
        mpBindTbl.setSubscribeScene(subscribeScene);
        mpBindTbl.setSubscribeTime(now);
        mpBindTbl.setModifyTime(now);
        mpBindTblDao.saveOrUpdateById(mpBindTbl);
    }

    /**
     * 更新取消关注用户信息
     * @param appId
     * @param openId
     */
    @Async
    public void updateMaBindByUnSubscribe(String appId,String openId) {
       LocalDateTime now = LocalDateTime.now();
       LambdaUpdateWrapper query = new LambdaUpdateWrapper<MpBindTbl>()
               .eq(MpBindTbl::getAppId,appId)
               .eq(MpBindTbl::getOpenId,openId)
               .eq(MpBindTbl::getSubscribe,1);
       MpBindTbl mpBindTbl = new MpBindTbl();
       mpBindTbl.setSubscribe(0);
       mpBindTbl.setUnsubscribeTime(now);
       mpBindTbl.setModifyTime(now);
       mpBindTblDao.update(mpBindTbl,query);
   }
}
