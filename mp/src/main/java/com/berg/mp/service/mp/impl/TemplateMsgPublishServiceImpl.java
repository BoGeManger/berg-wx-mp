package com.berg.mp.service.mp.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.berg.dao.page.PageInfo;
import com.berg.dao.system.mp.entity.MsgPublishTbl;
import com.berg.dao.system.mp.service.MsgPublishTblDao;
import com.berg.mp.service.AbstractService;
import com.berg.mp.service.mp.TemplateMsgPublishService;
import com.berg.vo.common.PageInVo;
import com.berg.vo.mp.MsgPublishVo;
import com.berg.vo.mp.in.GetMsgPublishPageInVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemplateMsgPublishServiceImpl extends AbstractService implements TemplateMsgPublishService {

    @Autowired
    MsgPublishTblDao msgPublishTblDao;

    /**
     * 获取模板消息发布分页列表
     * @param input
     * @return
     */
    @Override
    public PageInfo<MsgPublishVo> getMsgPublishPage(GetMsgPublishPageInVo input){
        return msgPublishTblDao.page(input,()->{
            LambdaQueryWrapper query = new LambdaQueryWrapper<MsgPublishTbl>()
                    .eq(MsgPublishTbl::getAppId,input.getAppId())
                    .eq(input.getStatus()!=null,MsgPublishTbl::getStatus,input.getStatus())
                    .eq(MsgPublishTbl::getPublishPeople,1)
                    .like(StringUtils.isNotBlank(input.getRemark()),MsgPublishTbl::getRemark,input.getRemark())
                    .orderByDesc(MsgPublishTbl::getCreateTime);
            return msgPublishTblDao.list(query,MsgPublishVo.class);
        });
    }

    /**
     * 获取已订阅模板消息分页列表
     * @param input
     * @return
     */
    @Override
    public PageInfo<MsgPublishVo> getMsgSubscribePublishPage(PageInVo input){
        return msgPublishTblDao.page(input,()->{
            String openId = getOpenId();
            return msgPublishTblDao.getMapper().listMsgSubscribePublish(getAppId(),openId);
        });
    }
}
