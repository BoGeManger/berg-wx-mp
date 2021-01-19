package com.berg.mp.service.mp.impl;

import cn.hutool.json.JSONUtil;
import com.berg.common.constant.RedisKeyConstants;
import com.berg.dao.system.mp.entity.KeysReplyTbl;
import com.berg.mp.service.mp.KeysReplyService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutNewsMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class KeysReplyServiceImpl implements KeysReplyService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * 根据关键字自动回复
     * @param wxMpXmlMessage
     * @param appId
     * @param content
     * @return
     */
    public WxMpXmlOutMessage sendByKeyReply(WxMpXmlMessage wxMpXmlMessage,String appId,String content){
        WxMpXmlOutMessage wxMpXmlOutMessage = null;
        String key = String.format(RedisKeyConstants.Mp.MP_KEYS_REPLY_SET, appId, content);
        String str = stringRedisTemplate.opsForSet().randomMember(key);
        if(StringUtils.isNotBlank(str)){
            KeysReplyTbl keysReplyTbl = JSONUtil.toBean(str, KeysReplyTbl.class);
            wxMpXmlOutMessage = getMsgHandle(wxMpXmlMessage,keysReplyTbl,content);
        }
        return wxMpXmlOutMessage;
    }

    /**
     * 获取消息处理
     *
     * @param wxMpXmlMessage
     * @param keysReplyTbl
     * @param content
     * @return
     */
    WxMpXmlOutMessage getMsgHandle(WxMpXmlMessage wxMpXmlMessage, KeysReplyTbl keysReplyTbl, String content) {
        WxMpXmlOutMessage wxMpXmlOutMessage = null;
        switch (keysReplyTbl.getReplyType()) {
            case 0://文本
                wxMpXmlOutMessage = WxMpXmlOutMessage.TEXT().content(content)
                        .fromUser(wxMpXmlMessage.getToUser()).toUser(wxMpXmlMessage.getFromUser())
                        .build();
                break;
            case 1://图文
                WxMpXmlOutNewsMessage.Item item = new WxMpXmlOutNewsMessage.Item();
                item.setTitle(keysReplyTbl.getReplyContent());
                item.setUrl(keysReplyTbl.getUrl());
                item.setPicUrl(keysReplyTbl.getPicUrl());
                item.setDescription(keysReplyTbl.getRemark());
                wxMpXmlOutMessage = WxMpXmlOutMessage.NEWS().addArticle(item)
                        .fromUser(wxMpXmlMessage.getToUser()).toUser(wxMpXmlMessage.getFromUser())
                        .build();
                break;
            case 2://素材
                wxMpXmlOutMessage = getMsgMediaHandle(wxMpXmlMessage, keysReplyTbl);
                break;
            default:
                wxMpXmlOutMessage = null;
                break;
        }
        return wxMpXmlOutMessage;
    }

    /**
     * 获取素材消息处理
     *
     * @param wxMpXmlMessage
     * @param keysReplyTbl
     * @return
     */
    WxMpXmlOutMessage getMsgMediaHandle(WxMpXmlMessage wxMpXmlMessage, KeysReplyTbl keysReplyTbl) {
        WxMpXmlOutMessage wxMpXmlOutMessage = null;
        switch (keysReplyTbl.getMediaType()) {
            case "image":
            case "thumb":
                wxMpXmlOutMessage = WxMpXmlOutMessage.IMAGE().mediaId(keysReplyTbl.getMediaId())
                        .fromUser(wxMpXmlMessage.getToUser()).toUser(wxMpXmlMessage.getFromUser())
                        .build();
                break;
            case "video":
                wxMpXmlOutMessage = WxMpXmlOutMessage.VIDEO().mediaId(keysReplyTbl.getMediaId())
                        .fromUser(wxMpXmlMessage.getToUser()).toUser(wxMpXmlMessage.getFromUser())
                        .build();
                break;
            case "voice":
                wxMpXmlOutMessage = WxMpXmlOutMessage.VOICE().mediaId(keysReplyTbl.getMediaId())
                        .fromUser(wxMpXmlMessage.getToUser()).toUser(wxMpXmlMessage.getFromUser())
                        .build();
                break;
            default:
                wxMpXmlOutMessage = null;
                break;
        }
        return wxMpXmlOutMessage;
    }
}
