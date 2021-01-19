package com.berg.mp.handler;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MenuHandler implements WxMpMessageHandler {

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map,
                                    WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
//        String msg = String.format("type:%s, event:%s, key:%s",
//                wxMpXmlMessage.getMsgType(), wxMpXmlMessage.getEvent(),
//                wxMpXmlMessage.getEventKey());
//        if (EventType.VIEW.equals(wxMpXmlMessage.getEvent())) {
//            return null;
//        }
//        return WxMpXmlOutMessage.TEXT().content(msg)
//            .fromUser(wxMpXmlMessage.getToUser()).toUser(wxMpXmlMessage.getFromUser())
//            .build();
        return null;
    }

}
