package com.berg.mp.handler;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.berg.common.constant.RedisKeyConstants;
import com.berg.dao.system.mp.entity.KeysReplyTbl;
import com.berg.mp.service.mp.KeysReplyService;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutNewsMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MsgHandler implements WxMpMessageHandler {

    @Autowired
    KeysReplyService keysReplyService;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map,
                                    WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
        //TODO 可接入人工智能回复
        //查询关键字并回复
        WxMpXmlOutMessage wxMpXmlOutMessage = null;
        String content = wxMpXmlMessage.getContent();
        String appId = wxMpXmlMessage.getAuthorizeAppId();
        keysReplyService.sendByKeyReply(wxMpXmlMessage,appId,content);
        return wxMpXmlOutMessage;
    }

}
