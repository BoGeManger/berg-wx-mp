package com.berg.common.constant;

public class RedisKeyConstants {

    public static final class System {
        //授权TOKEN(token)
        public static final String SYSTEM_TOKEN = "system_token_%s";
        //用户角色信息(username)
        public static final String USER_ROLE = "user_role_%s";
        //用户组件权限信息(username)
        public static final String USER_PERMS = "user_perms_%s";
    }

    public static final class Mp{
        //微信公众号授权校验(appId,openId)
        public static final String MP_TOKEN = "mp_token_%s_%s";
        //微信公众号网页授权(appId,openId)
        public static final String MP_OAUTH_ACCESS_TOKEN = "mp_oauth_access_token_%s_%s";
        //微信公众号刷新网页授权(appId,openId)
        public static final String MP_OAUTH_REFRESH_ACCESS_TOKEN = "mp_oauth_refresh_access_token_%s_%s";
        //微信公众号会员注册验证码(appId,phone)
        public static final String MP_VERIFY_CODE = "mp_verify_code_%s_%s";
        //微信公众号模板消息列表(appId)
        public static final String MP_TEMPLATE_LIST = "mp_template_list_%s";
        //微信公众号关键字回复Set(appId,keyContent)
        public static final String MP_KEYS_REPLY_SET= "mp_keys_reply_set_%s_%s";
        //微信公众号活动二维码事件Set(appId,code.event)
        public static final String MP_ACTIVITY_QRCODE_EVENT_SET ="mp_activity_qrcode_event_set_%s_%s_%s";
    }

}
