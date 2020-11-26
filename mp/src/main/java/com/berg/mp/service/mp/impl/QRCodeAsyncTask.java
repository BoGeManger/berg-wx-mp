package com.berg.mp.service.mp.impl;
import java.time.LocalDateTime;

import com.berg.dao.system.mp.entity.QrcodeTbl;
import com.berg.dao.system.mp.service.QrcodeTblDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class QRCodeAsyncTask {

    @Autowired
    QrcodeTblDao qrcodeTblDao;

    /**
     * 新增二维码记录
     * @param appId
     * @param openId
     * @param sceneStr
     * @param expireSeconds
     * @param ticket
     * @param url
     * @param remark
     * @param type
     */
    @Async
    public void addQRCode(String appId,String openId,String sceneStr,Integer expireSeconds,String ticket,String url,String remark,Integer type){
        QrcodeTbl qrcodeTbl = new QrcodeTbl();
        qrcodeTbl.setAppId(appId);
        qrcodeTbl.setOpenId(openId);
        qrcodeTbl.setSceneStr(sceneStr);
        qrcodeTbl.setExpireSeconds(expireSeconds);
        qrcodeTbl.setTicket(ticket);
        qrcodeTbl.setUrl(url);
        qrcodeTbl.setRemark(remark);
        qrcodeTbl.setType(type);
        qrcodeTbl.setCreateTime(LocalDateTime.now());
        qrcodeTblDao.save(qrcodeTbl);
    }
}
