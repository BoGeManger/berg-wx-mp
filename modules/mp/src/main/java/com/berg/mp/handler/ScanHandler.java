package com.berg.mp.handler;

import com.berg.mp.service.mp.ActivityQRCodeService;
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
public class ScanHandler implements WxMpMessageHandler {

    @Autowired
    ActivityQRCodeService activityQRCodeService;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map,
                                    WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
        WxMpXmlOutMessage wxMpXmlOutMessage = null;
        String appId = wxMpXmlMessage.getAuthorizeAppId();
        wxMpXmlOutMessage = activityQRCodeService.sendByEvent(wxMpXmlMessage,appId,"scan");
        return wxMpXmlOutMessage;
    }
}
