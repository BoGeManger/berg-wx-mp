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
public class SubscribeHandler implements WxMpMessageHandler {

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map,
                                    WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
//        log.info("新关注用户 OPENID: " + wxMpXmlMessage.getFromUser());
//        // 获取微信用户基本信息
//        try {
//            WxMpUser userWxInfo = wxMpService.getUserService()
//                .userInfo(wxMpXmlMessage.getFromUser(), null);
//            if (userWxInfo != null) {
                //TODO 可以添加关注用户到本地数据库
//            }
//        } catch (WxErrorException e) {
//            if (e.getError().getErrorCode() == 48001) {
//                log.info("该公众号没有获取用户信息权限！");
//            }
//        }
//        WxMpXmlOutMessage responseResult = null;
//        try {
//            responseResult = this.handleSpecial(wxMpXmlMessage);
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//        }
//
//        if (responseResult != null) {
//            return responseResult;
//        }
//
//        try {
//            return WxMpXmlOutMessage.TEXT().content("感谢关注")
//                    .fromUser(wxMpXmlMessage.getToUser()).toUser(wxMpXmlMessage.getFromUser())
//                    .build();
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//        }
        return null;
    }

    /**
     * 处理特殊请求，比如如果是扫码进来的，可以做相应处理
     */
//    WxMpXmlOutMessage handleSpecial(WxMpXmlMessage wxMessage) throws Exception {
          //TODO
//        return null;
//    }

}
