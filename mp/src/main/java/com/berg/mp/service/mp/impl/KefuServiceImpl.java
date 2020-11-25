package com.berg.mp.service.mp.impl;

import com.berg.exception.FailException;
import com.berg.mp.service.base.BaseService;
import com.berg.mp.service.mp.KefuService;
import com.berg.vo.mp.in.MpKefuSendTextInVo;
import com.berg.wx.mp.utils.WxMpUtil;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import org.springframework.stereotype.Service;

@Service
public class KefuServiceImpl extends BaseService implements KefuService {

    /**
     * 发送客服文本消息
     * @param input
     */
    @Override
    public Boolean sendText(MpKefuSendTextInVo input){
        Boolean flag = true;
        try{
            WxMpKefuMessage message = WxMpKefuMessage.TEXT().toUser(input.getToUser()).content(input.getContent()).build();;
            WxMpUtil.getService(getAppId()).getKefuService().sendKefuMessage(message);
        }catch (Exception ex){
            throw new FailException("调用公众号发送客服文本消息接口sendText失败:"+ex.getMessage());
        }
        return flag;
    }
}
