package com.berg.mp.service.mp;

import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

public interface KeysReplyService {

    WxMpXmlOutMessage sendByKeyReply(WxMpXmlMessage wxMpXmlMessage, String appId, String content);
}
