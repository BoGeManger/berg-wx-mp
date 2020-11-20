package com.berg.constant;


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
	}

}
