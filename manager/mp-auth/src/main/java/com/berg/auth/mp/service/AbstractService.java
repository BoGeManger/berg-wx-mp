package com.berg.auth.mp.service;

import com.berg.auth.mp.auth.AuthenticationUtil;
import com.berg.vo.mp.MpUserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Service
public abstract class AbstractService {

    @Autowired
    public AuthenticationUtil authenticationUtil;

    public String getAppId(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        return request.getHeader("appId");
    }

    /**
     * 从token中获取openId
     * @return
     */
    public String getOpenId() {
        return authenticationUtil.getOpenId();
    }

    /**
     * 从 token中获取unionId
     */
    public String getUnionId() {
        return authenticationUtil.getUnionId();
    }

    /**
     * 从 token中获取memberId
     */
    public String getMemberId() {
        return authenticationUtil.getMemberId();
    }

    /**
     * 从 token中获取maBindId
     */
    public Integer getMaBindId() {
        return authenticationUtil.getMaBindId();
    }

    /**
     * 从 token中获取createTime
     */
    public LocalDateTime getCreateTime() {
        return authenticationUtil.getCreateTime();
    }

    /**
     * 从 token中获取modifyTime
     */
    public LocalDateTime getModifyTime() {
        return authenticationUtil.getModifyTime();
    }

    /**
     * 从 token中获取userinfo
     */
    public MpUserInfoVo getUserinfo() {
        return authenticationUtil.getUserinfo();
    }

    /**
     * 从 token中获取oauthAccessToken
     */
    public String getOauthAccessToken(){
        return authenticationUtil.getOauthAccessToken();
    }

    /**
     * 获取token
     * @return
     */
    public String getToken(){
        return authenticationUtil.getToken();
    }
}
