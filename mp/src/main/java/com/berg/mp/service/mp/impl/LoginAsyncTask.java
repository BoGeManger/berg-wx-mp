package com.berg.mp.service.mp.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.berg.common.constant.RedisKeyConstants;
import com.berg.dao.system.mb.entity.MpBindTbl;
import com.berg.dao.system.mb.service.MpBindTblDao;
import com.berg.mp.auth.JWTToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Component
public class LoginAsyncTask {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    MpBindTblDao mpBindTblDao;

    /**
     * 新增绑定信息
     *
     * @param appId
     * @param openId
     * @param unionId
     */
    @Async
    public void addMpBind(String appId, String openId, String unionId) {
        LocalDateTime now = LocalDateTime.now();
        MpBindTbl mpBindTbl = new MpBindTbl();
        mpBindTbl.setAppId(appId);
        mpBindTbl.setUnionId(unionId);
        mpBindTbl.setOpenId(openId);
        mpBindTbl.setCreateTime(now);
        mpBindTbl.setModifyTime(now);
        mpBindTblDao.save(mpBindTbl);
    }

    /**
     * 更新绑定变更的unionId
     *
     * @param appId
     * @param openId
     * @param unionId
     */
    @Async
    public void updateMpBindUnionId(String appId, String openId, String unionId) {
        LambdaQueryWrapper query = new LambdaQueryWrapper<MpBindTbl>()
                .eq(MpBindTbl::getAppId, appId)
                .eq(MpBindTbl::getOpenId, openId)
                .ne(MpBindTbl::getUnionId, "")
                .ne(MpBindTbl::getUnionId, unionId)
                .isNotNull(MpBindTbl::getUnionId);
        MpBindTbl mpBindTbl = new MpBindTbl();
        mpBindTbl.setUnionId(unionId);
        mpBindTbl.setModifyTime(LocalDateTime.now());
        mpBindTblDao.update(mpBindTbl, query);
    }

    /**
     * 设置授权校验缓存
     *
     * @param jwtToken
     */
    @Async
    public void setTokenCache(JWTToken jwtToken) {
        String key = String.format(RedisKeyConstants.Mp.MP_TOKEN,jwtToken.getAppId().toLowerCase(), jwtToken.getOpenId().toLowerCase());
        stringRedisTemplate.opsForValue().set(key, jwtToken.getToken(),jwtToken.getExipreAt(), TimeUnit.DAYS);
    }

    /**
     * 删除授权校验缓存
     *
     * @param openId
     */
    @Async
    public void delTokenCache(String openId) {
        if(StringUtils.isNotBlank(openId)) {
            String key = String.format(RedisKeyConstants.Mp.MP_TOKEN, openId);
            stringRedisTemplate.delete(key);
        }
    }

    /**
     * 设置微信公众号网页授权校验缓存
     * @param accessToken
     * @param exipre
     * @param refreshToken
     */
    @Async
    public void setOAuthAccessTokenCache(String appId,String openId,String accessToken,Integer exipre,String refreshToken){
        String accessTokenKey = String.format(RedisKeyConstants.Mp.MP_OAUTH_ACCESS_TOKEN,appId.toLowerCase(), openId.toLowerCase());
        String refreshAccessTokenKey = String.format(RedisKeyConstants.Mp.MP_OAUTH_REFRESH_ACCESS_TOKEN,appId.toLowerCase(), openId.toLowerCase());
        stringRedisTemplate.opsForValue().set(accessTokenKey, accessToken,exipre, TimeUnit.SECONDS);
        stringRedisTemplate.opsForValue().set(refreshAccessTokenKey,refreshToken,30,TimeUnit.DAYS);//刷新缓存官方默认30天
    }
}
