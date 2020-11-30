package com.berg.mp.service.mp.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.berg.dao.system.mp.entity.MsgSubscribeTbl;
import com.berg.dao.system.mp.service.MsgSubscribeTblDao;
import com.berg.mp.service.base.BaseService;
import com.berg.mp.service.mp.TemplateMsgSubscribeService;
import com.berg.vo.mp.in.TemplateSubscribeInVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TemplateMsgSubscribeServiceImpl extends BaseService implements TemplateMsgSubscribeService {

    @Autowired
    MsgSubscribeTblDao msgSubscribeTblDao;

    /**
     * 订阅模板消息
     * @param input
     */
    @Override
    public void subscribe(TemplateSubscribeInVo input){
        String appId = getAppId();
        String openId = jWTUtil.getOpenId();
        String unionId = jWTUtil.getUnionId();
        String memberId = jWTUtil.getMemberId();
        LocalDateTime now = LocalDateTime.now();
        MsgSubscribeTbl msgSubscribeTbl = new MsgSubscribeTbl();
        msgSubscribeTbl.setAppId(appId);
        msgSubscribeTbl.setPublishId(input.getPublishId());
        msgSubscribeTbl.setUnionId(unionId);
        msgSubscribeTbl.setOpenId(openId);
        msgSubscribeTbl.setMemberId(memberId);
        msgSubscribeTbl.setExecuteNum(0);
        msgSubscribeTbl.setRemark(input.getRemark());
        msgSubscribeTbl.setCreateTime(now);
        msgSubscribeTbl.setModifyTime(now);
        msgSubscribeTbl.setStatus(0);
        msgSubscribeTblDao.save(msgSubscribeTbl);
    }

    /**
     * 取消订阅模板消息
     * @param publishId
     */
    @Override
    public void unsubscribe(String publishId){
        String appId = getAppId();
        String openId = jWTUtil.getOpenId();
        LambdaUpdateWrapper query = new LambdaUpdateWrapper<MsgSubscribeTbl>()
                .eq(MsgSubscribeTbl::getPublishId,publishId)
                .eq(MsgSubscribeTbl::getAppId,appId)
                .eq(MsgSubscribeTbl::getOpenId,openId)
                .eq(MsgSubscribeTbl::getStatus,0);
        msgSubscribeTblDao.update(query);
    }
}
