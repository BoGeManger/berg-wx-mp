package com.berg.mp.service.mp.impl;

import com.berg.exception.FailException;
import com.berg.exception.ParamException;
import com.berg.mp.service.mp.PortalService;
import com.berg.wx.miniapp.utils.WxMaUtil;
import com.berg.wx.mp.utils.WxMpUtil;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class PortalServiceImpl implements PortalService {

    public void get(String appId,String signature,String timestamp,String nonce,String echostr) {
//        if (StringUtils.isAnyBlank(signature, timestamp, nonce, echostr)) {
//            throw new ParamException("请求参数非法，请核实!");
//        }
//        try{
//            WxMpService wxMpService = WxMpUtil.getService(appId);
//            if (!wxMpService.switchover(appId)) {
//                throw new ParamException(String.format("未找到对应appid=[%s]的配置，请核实！", appId));
//            }
//            if (wxMpService.checkSignature(timestamp, nonce, signature)) {
//                //处理消息
//            }
//        }catch (Exception ex){
//            throw new FailException("接收微信服务器认证消息失败:"+ex.getMessage());
//        }
    }

    public void post(String appId,String signature,String timestamp,String nonce,String openid,String encType,String msgSignature,String requestBody) {
//        try{
//            WxMpService wxMpService = WxMpUtil.getService(appId);
//            if (!wxMpService.switchover(appId)) {
//                throw new ParamException(String.format("未找到对应appid=[%s]的配置，请核实！", appId));
//            }
//            if (!wxMpService.checkSignature(timestamp, nonce, signature)) {
//                throw new ParamException("非法请求，可能属于伪造的请求！");
//            }
//            String out = null;
//            WxMpMessageRouter wxMpMessageRouter =WxMpUtil.getRouter(appId);
//            if (encType == null) {
//                // 明文传输的消息
//                WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(requestBody);
//                WxMpXmlOutMessage outMessage = wxMpMessageRouter.route(inMessage);
//                out = outMessage.toXml();
//            } else if ("aes".equalsIgnoreCase(encType)) {
//                // aes加密的消息
//                WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(requestBody, wxMpService.getWxMpConfigStorage(),
//                        timestamp, nonce, msgSignature);
//                WxMpXmlOutMessage outMessage = wxMpMessageRouter.route(inMessage);
//                out = outMessage.toEncryptedXml(wxMpService.getWxMpConfigStorage());
//            }
//        }catch (Exception ex){
//            throw new FailException("接收微信请求失败:"+ex.getMessage());
//        }
    }
}
