package com.berg.mp.service.mp.impl;

import com.berg.constant.RedisKeyConstants;
import com.berg.mp.service.mp.ActivityQRCodeService;
import com.berg.mp.service.mp.KeysReplyService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ActivityQRCodeServiceImpl  implements ActivityQRCodeService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    KeysReplyService keysReplyService;

    @Autowired
    ActivityQRCodeAsyncTask activityQRCodeAsyncTask;

    /**
     * 根据活动二维码事件自动回复
     * @param wxMpXmlMessage
     * @param appId
     * @param event
     * @return
     */
    public WxMpXmlOutMessage sendByEvent(WxMpXmlMessage wxMpXmlMessage,String appId, String event){
        WxMpXmlOutMessage wxMpXmlOutMessage = null;
        String code = wxMpXmlMessage.getEventKey().replace("qrscene_","");
        String key = String.format(RedisKeyConstants.Mp.MP_ACTIVITY_QRCODE_EVENT_SET, appId, code,event);
        String content = stringRedisTemplate.opsForValue().get(key);
        if(StringUtils.isNotBlank(content)){
            wxMpXmlOutMessage = keysReplyService.sendByKeyReply(wxMpXmlMessage,appId,content);
        }
        activityQRCodeAsyncTask.addActivityQrcodeRecord(appId,wxMpXmlMessage.getFromUser(),code,event);
        return wxMpXmlOutMessage;
    }
}
