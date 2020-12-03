package com.berg.mp.handler;

import com.berg.mp.service.mp.ActivityQRCodeService;
import com.berg.mp.service.mp.KeysReplyService;
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
public class SubscribeHandler implements WxMpMessageHandler {

    @Autowired
    UserAsyncTask userAsyncTask;

    @Autowired
    KeysReplyService keysReplyService;
    @Autowired
    ActivityQRCodeService activityQRCodeService;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map,
                                    WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
        WxMpXmlOutMessage wxMpXmlOutMessage = null;
        String appId = wxMpXmlMessage.getAuthorizeAppId();
        //关注用户记录
        userAsyncTask.addOrUpdateMaBindBySubscribe(appId,wxMpXmlMessage.getFromUser(),wxMpXmlMessage.getScene());
        if(wxMpXmlMessage.getEventKey().contains("qrscene_")){//扫码关注
            wxMpXmlOutMessage = activityQRCodeService.sendByEvent(wxMpXmlMessage,appId,"subscribe");
        }else {//普通关注
            //查询关注关键字并回复
            wxMpXmlOutMessage = keysReplyService.sendByKeyReply(wxMpXmlMessage,appId,"关注");
        }
        return wxMpXmlOutMessage;
    }

}
