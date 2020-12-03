package com.berg.mp.handler;

import com.berg.mp.service.mp.impl.UserAsyncTask;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UnsubscribeHandler implements WxMpMessageHandler {

    @Autowired
    UserAsyncTask userAsyncTask;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map,
                                    WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
        //取消关注用户记录
        userAsyncTask.updateMaBindByUnSubscribe(wxMpXmlMessage.getAuthorizeAppId(),wxMpXmlMessage.getFromUser());
        return null;
    }

}
