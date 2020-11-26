package com.berg.system.service.mp.impl;

import com.berg.dao.system.mp.entity.MsgRecordTbl;
import com.berg.dao.system.mp.service.MsgRecordTblDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TemplateMsgAsyncTask {

    @Autowired
    MsgRecordTblDao msgRecordTblDao;

    /**
     * 新增消息记录
     * @param appId
     * @param openId
     * @param msgId
     * @param templateId
     * @param data
     * @param url
     * @param miniappAppid
     * @param miniappPage
     * @param userMiniappPath
     * @param remark
     */
    @Async
    public void addMsgRecord(String appId,String openId,String msgId,String templateId,String data
            ,String url,String miniappAppid,String miniappPage,Integer userMiniappPath,String remark){
        MsgRecordTbl msgRecordTbl = new MsgRecordTbl();
        msgRecordTbl.setAppId(appId);
        msgRecordTbl.setOpenId(openId);
        msgRecordTbl.setMsgId(msgId);
        msgRecordTbl.setTemplateId(templateId);
        msgRecordTbl.setData(data);
        msgRecordTbl.setUrl(url);
        msgRecordTbl.setMiniappAppid(miniappAppid);
        msgRecordTbl.setMiniappPage(miniappPage);
        msgRecordTbl.setUserMiniappPath(userMiniappPath);
        msgRecordTbl.setRemark(remark);
        msgRecordTbl.setCreateTime(LocalDateTime.now());
        msgRecordTblDao.save(msgRecordTbl);
    }
}
