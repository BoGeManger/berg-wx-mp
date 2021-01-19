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
public class LocationHandler implements WxMpMessageHandler {

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map,
                                    WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
//        if (wxMpXmlMessage.getMsgType().equals(XmlMsgType.LOCATION)) {
            //TODO 接收处理用户发送的地理位置消息
//            try {
//                String content = "感谢反馈，您的的地理位置已收到！";
//                return WxMpXmlOutMessage.TEXT().content(content)
//                        .fromUser(wxMpXmlMessage.getToUser()).toUser(wxMpXmlMessage.getFromUser())
//                        .build();
//            } catch (Exception e) {
//                log.error("位置消息接收处理失败", e);
//                return null;
//            }
//        }
//        //上报地理位置事件
//        log.info("上报地理位置，纬度 : {}，经度 : {}，精度 : {}",
//                wxMpXmlMessage.getLatitude(), wxMpXmlMessage.getLongitude(), String.valueOf(wxMpXmlMessage.getPrecision()));
        //TODO  可以将用户地理位置信息保存到本地数据库，以便以后使用
        return null;
    }

}
