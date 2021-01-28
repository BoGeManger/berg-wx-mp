package com.berg.mp.service.mp;

import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

public interface ActivityQRCodeService {

    String getKeyReply(WxMpXmlMessage wxMpXmlMessage, String appId, String event);
}
