package com.berg.system.service.mp.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.berg.dao.page.PageInfo;
import com.berg.dao.system.mp.entity.ShortUrlTbl;
import com.berg.dao.system.mp.service.ShortUrlTblDao;
import com.berg.exception.FailException;
import com.berg.system.auth.JWTUtil;
import com.berg.system.service.mp.ShortUrlService;
import com.berg.vo.mp.ShortUrlVo;
import com.berg.vo.mp.in.GetShortUrlPageInVo;
import com.berg.vo.mp.in.MpCreateShortUrlInVo;
import com.berg.wx.mp.utils.WxMpUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ShortUrlServiceImpl implements ShortUrlService {

    @Autowired
    JWTUtil jwtUtil;

    @Autowired
    ShortUrlTblDao shortUrlTblDao;

    /**
     * 获取短连接记录分页列表
     * @param input
     * @return
     */
    @Override
    public PageInfo<ShortUrlVo> getShortUrlPage(GetShortUrlPageInVo input){
        return shortUrlTblDao.page(input,()->{
            LambdaQueryWrapper<ShortUrlTbl> query = new LambdaQueryWrapper<ShortUrlTbl>()
                    .eq(ShortUrlTbl::getAppId,input.getAppId())
                    .like(StringUtils.isNotBlank(input.getRemark()),ShortUrlTbl::getRemark,input.getRemark());
            return shortUrlTblDao.list(query,ShortUrlVo.class);
        });
    }

    /**
     * 生成短连接
     * @param input
     * @return
     */
    @Override
    public String create(MpCreateShortUrlInVo input){
        String shortUrl ="";
        try{
            shortUrl = WxMpUtil.getService(input.getAppId()).shortUrl(input.getLongUrl());
            addShortUrlTbl(input.getAppId(),input.getLongUrl(),shortUrl,input.getRemark());
        }catch (Exception ex){
            throw new FailException("调用公众号生成短连接接口create失败:"+ex.getMessage());
        }
        return shortUrl;
    }

    /**
     * 新增短连接记录
     * @param appId
     * @param longUrl
     * @param shortUrl
     * @param remark
     */
    void addShortUrlTbl(String appId,String longUrl,String shortUrl,String remark){
        LocalDateTime now = LocalDateTime.now();
        ShortUrlTbl shortUrlTbl = new ShortUrlTbl();
        shortUrlTbl.setAppId(appId);
        shortUrlTbl.setLongUrl(longUrl);
        shortUrlTbl.setShortUrl(shortUrl);
        shortUrlTbl.setCreateTime(now);
        shortUrlTbl.setCreateUser(jwtUtil.getUsername());
        shortUrlTbl.setRemark(remark);
        shortUrlTblDao.save(shortUrlTbl);
    }

}
