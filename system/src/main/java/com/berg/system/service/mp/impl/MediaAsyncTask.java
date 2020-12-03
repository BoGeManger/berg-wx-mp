package com.berg.system.service.mp.impl;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.berg.dao.system.mp.entity.MediaTbl;
import com.berg.dao.system.mp.service.MediaTblDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class MediaAsyncTask {

    @Autowired
    MediaTblDao mediaTblDao;

    /**
     * 新增素材
     * @param appId
     * @param mediaId
     * @param url
     * @param createAt
     * @param mediaType
     * @param type
     * @param remark
     * @param user
     */
    @Async
    public void addMedia(String appId,String mediaId,String url,Long createAt,String mediaType,Integer type,String remark,String user){
        LocalDateTime now = LocalDateTime.now();
        MediaTbl mediaTbl = new MediaTbl();
        mediaTbl.setAppId(appId);
        mediaTbl.setMediaId(mediaId);
        mediaTbl.setUrl(url);
        mediaTbl.setCreatedAt(createAt.intValue());
        mediaTbl.setMediaType(mediaType);
        mediaTbl.setType(type);
        mediaTbl.setRemark(remark);
        mediaTbl.setCreateTime(now);
        mediaTbl.setCreateUser(user);
        mediaTbl.setModifyTime(now);
        mediaTbl.setModifyUser(user);
        mediaTbl.setIsdel(0);
        mediaTblDao.save(mediaTbl);
    }

    /**
     * 修改素材
     * @param appId
     * @param mediaId
     * @param user
     */
    @Async
    public void updateMedia(String appId,String mediaId,String user){
        LambdaUpdateWrapper query = new LambdaUpdateWrapper<MediaTbl>().eq(MediaTbl::getAppId,appId).eq(MediaTbl::getMediaId,mediaId);
        MediaTbl mediaTbl = new MediaTbl();
        mediaTbl.setModifyTime(LocalDateTime.now());
        mediaTbl.setModifyUser(user);
        mediaTblDao.update(mediaTbl,query);
    }

    /**
     * 删除素材
     * @param appId
     * @param mediaId
     * @param user
     */
    @Async
    public void delMedia(String appId,String mediaId,String user){
        LambdaUpdateWrapper query = new LambdaUpdateWrapper<MediaTbl>().eq(MediaTbl::getAppId,appId).eq(MediaTbl::getMediaId,mediaId);
        MediaTbl mediaTbl = new MediaTbl();
        mediaTbl.setDelTime(LocalDateTime.now());
        mediaTbl.setDelUser(user);
        mediaTbl.setIsdel(1);
        mediaTblDao.update(mediaTbl,query);
    }
}
