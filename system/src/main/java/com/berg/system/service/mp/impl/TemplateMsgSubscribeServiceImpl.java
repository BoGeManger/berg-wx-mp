package com.berg.system.service.mp.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.berg.dao.page.PageInfo;
import com.berg.dao.system.mp.entity.MsgSubscribeTbl;
import com.berg.dao.system.mp.service.MsgSubscribeTblDao;
import com.berg.system.service.mp.TemplateMsgSubscribeService;
import com.berg.vo.mp.MsgSubscribeVo;
import com.berg.vo.mp.in.GetMsgSubscribePageInVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemplateMsgSubscribeServiceImpl implements TemplateMsgSubscribeService {

    @Autowired
    MsgSubscribeTblDao msgSubscribeTblDao;

    /**
     * 获取模板消息订阅分页列表
     * @param input
     * @return
     */
    @Override
    public PageInfo<MsgSubscribeVo> getMsgSubscribePage(GetMsgSubscribePageInVo input){
        return msgSubscribeTblDao.page(input,()->{
            LambdaQueryWrapper query = new LambdaQueryWrapper<MsgSubscribeTbl>()
                    .eq(MsgSubscribeTbl::getAppId,input.getAppId())
                    .eq(MsgSubscribeTbl::getPublishId,input.getPublishId())
                    .eq(StringUtils.isNotBlank(input.getOpenId()),MsgSubscribeTbl::getOpenId,input.getOpenId())
                    .eq(input.getStatus()!=null,MsgSubscribeTbl::getStatus,input.getStatus())
                    .like(StringUtils.isNotBlank(input.getRemark()),MsgSubscribeTbl::getRemark,input.getRemark())
                    .orderByDesc(MsgSubscribeTbl::getCreateTime);
            return msgSubscribeTblDao.list(query,MsgSubscribeVo.class);
        });

    }


}
