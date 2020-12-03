package com.berg.mp.service.mp.impl;
import java.time.LocalDateTime;

import com.berg.dao.system.mp.entity.ActivityQrcodeRecordTbl;
import com.berg.dao.system.mp.service.ActivityQrcodeRecordTblDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivityQRCodeAsyncTask {

    @Autowired
    ActivityQrcodeRecordTblDao activityQrcodeRecordTblDao;

    /**
     * 新增微信公众号活动二维码记录
     * @param appId
     * @param openId
     * @param code
     * @param event
     */
    public void addActivityQrcodeRecord(String appId,String openId,String code,String event){
        ActivityQrcodeRecordTbl activityQrcodeRecordTbl = new ActivityQrcodeRecordTbl();
        activityQrcodeRecordTbl.setAppId(appId);
        activityQrcodeRecordTbl.setOpenId(openId);
        activityQrcodeRecordTbl.setCode(code);
        activityQrcodeRecordTbl.setEvent(event);
        activityQrcodeRecordTbl.setCreateTime(LocalDateTime.now());
        activityQrcodeRecordTblDao.save(activityQrcodeRecordTbl);
    }
}
