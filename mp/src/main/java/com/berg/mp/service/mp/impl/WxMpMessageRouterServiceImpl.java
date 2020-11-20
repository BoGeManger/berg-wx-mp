package com.berg.mp.service.mp.impl;

import com.berg.mp.handler.*;
import com.berg.wx.mp.service.WxMpMessageRouterService;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static me.chanjar.weixin.common.api.WxConsts.EventType.SUBSCRIBE;
import static me.chanjar.weixin.common.api.WxConsts.EventType.UNSUBSCRIBE;
import static me.chanjar.weixin.common.api.WxConsts.XmlMsgType.EVENT;
import static me.chanjar.weixin.mp.constant.WxMpEventConstants.CustomerService.*;
import static me.chanjar.weixin.mp.constant.WxMpEventConstants.POI_CHECK_NOTIFY;

@Service
public class WxMpMessageRouterServiceImpl implements WxMpMessageRouterService {

    @Autowired
    LogHandler logHandler;
    @Autowired
    KfSessionHandler kfSessionHandler;
    @Autowired
    StoreCheckNotifyHandler storeCheckNotifyHandler;
    @Autowired
    MenuHandler menuHandler;
    @Autowired
    NullHandler nullHandler;
    @Autowired
    SubscribeHandler subscribeHandler;
    @Autowired
    UnsubscribeHandler unsubscribeHandler;
    @Autowired
    LocationHandler locationHandler;
    @Autowired
    ScanHandler scanHandler;
    @Autowired
    MsgHandler msgHandler;

    @Override
    public WxMpMessageRouter createRouter(WxMpService service) {
        final WxMpMessageRouter newRouter = new WxMpMessageRouter(service);
        // 记录所有事件的日志 （异步执行）
        newRouter.rule().handler(logHandler).next();
        // 接收客服会话管理事件
        newRouter.rule().async(false).msgType(EVENT).event(KF_CREATE_SESSION)
                .handler(kfSessionHandler).end();
        newRouter.rule().async(false).msgType(EVENT).event(KF_CLOSE_SESSION)
                .handler(kfSessionHandler).end();
        newRouter.rule().async(false).msgType(EVENT).event(KF_SWITCH_SESSION)
                .handler(kfSessionHandler).end();
        // 门店审核事件
        newRouter.rule().async(false).msgType(EVENT).event(POI_CHECK_NOTIFY).handler(storeCheckNotifyHandler).end();
        // 自定义菜单事件
        newRouter.rule().async(false).msgType(EVENT).event(WxConsts.EventType.CLICK).handler(menuHandler).end();
        // 点击菜单连接事件
        newRouter.rule().async(false).msgType(EVENT).event(WxConsts.EventType.VIEW).handler(nullHandler).end();
        // 关注事件
        newRouter.rule().async(false).msgType(EVENT).event(SUBSCRIBE).handler(subscribeHandler).end();
        // 取消关注事件
        newRouter.rule().async(false).msgType(EVENT).event(UNSUBSCRIBE).handler(unsubscribeHandler).end();
        // 上报地理位置事件
        newRouter.rule().async(false).msgType(EVENT).event(WxConsts.EventType.LOCATION).handler(locationHandler).end();
        // 接收地理位置消息
        newRouter.rule().async(false).msgType(WxConsts.XmlMsgType.LOCATION).handler(locationHandler).end();
        // 扫码事件
        newRouter.rule().async(false).msgType(EVENT).event(WxConsts.EventType.SCAN).handler(scanHandler).end();
        // 默认
        newRouter.rule().async(false).handler(msgHandler).end();
        return newRouter;
    }
}
