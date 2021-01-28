package com.berg.mp.handler;

import com.berg.mp.service.mp.ActivityQRCodeService;
import com.berg.mp.service.mp.KeysReplyService;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ScanHandler implements WxMpMessageHandler {

    @Autowired
    KeysReplyService keysReplyService;
    @Autowired
    ActivityQRCodeService activityQRCodeService;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map,
                                    WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
        WxMpXmlOutMessage wxMpXmlOutMessage = null;
        //触发活动二维码回复
        String appId = wxMpXmlMessage.getAuthorizeAppId();
        String conent = activityQRCodeService.getKeyReply(wxMpXmlMessage,appId,"scan");
        if(StringUtils.isNotBlank(conent)){
            wxMpXmlOutMessage = keysReplyService.sendByKeyReply(wxMpXmlMessage,appId,conent);
        }
        return wxMpXmlOutMessage;
    }
}
