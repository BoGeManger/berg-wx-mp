package com.berg.mp.service.mp.impl;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.berg.common.constant.RedisKeyConstants;
import com.berg.dao.system.mb.entity.MpBindTbl;
import com.berg.dao.system.mb.service.MpBindTblDao;
import com.berg.common.exception.FailException;
import com.berg.mp.auth.JWTToken;
import com.berg.mp.constant.MpConstants;
import com.berg.mp.service.base.BaseService;
import com.berg.mp.service.mp.LoginService;
import com.berg.vo.mp.MpUserInfoVo;
import com.berg.vo.mp.in.MpGetAuthUrlInVo;
import com.berg.vo.mp.in.MpLoginInVo;
import com.berg.vo.mp.in.MpRefreshInVo;
import com.berg.vo.mp.out.MpLoginOutVo;
import com.berg.wx.mp.utils.WxMpUtil;
import me.chanjar.weixin.common.bean.oauth2.WxOAuth2AccessToken;
import me.chanjar.weixin.mp.api.WxMpService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl extends BaseService implements LoginService {

    @Autowired
    MpConstants mpConstants;

    @Autowired
    LoginAsyncTask loginAsyncTask;

    @Autowired
    MpBindTblDao mpBindTblDao;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * 获取公众号网页授权地址
     * @param input
     * @return
     */
    @Override
    public String getAuthUrl(MpGetAuthUrlInVo input){
        String appId = getAppId();
        String url = null;
        try{
            url = WxMpUtil.getService(appId).getOAuth2Service().buildAuthorizationUrl(input.getRedirectUri(),input.getScope(),input.getState());
        }catch (Exception ex){
            throw new FailException("调用获取公众号网页授权地址接口getAuthUrl失败:"+ex.getMessage());
        }
        return url;
    }

    /**
     * 公众号登录
     * @param input
     * @return
     */
    @Override
    public MpLoginOutVo login(MpLoginInVo input){
        String appId = getAppId();
        WxOAuth2AccessToken wxOAuth2AccessToken = getOAuth2AccessToken(appId,input.getCode());
        MpLoginOutVo result = setMpLoginOutVo(appId,wxOAuth2AccessToken.getOpenId(),wxOAuth2AccessToken.getUnionId());
        //生成JWT
        JWTToken jwtToken = getJwt(result,wxOAuth2AccessToken.getAccessToken());
        //生成token缓存信息
        loginAsyncTask.setTokenCache(jwtToken);
        //生成oauthAccessToken缓存信息
        loginAsyncTask.setOAuthAccessTokenCache(appId,wxOAuth2AccessToken.getOpenId(),wxOAuth2AccessToken.getAccessToken(),wxOAuth2AccessToken.getExpiresIn(),wxOAuth2AccessToken.getRefreshToken());
        result.setToken(jwtToken.getToken());
        return result;
    }

    /**
     * 刷新登录校验并获取最新用户信息
     * @param input
     * @return
     */
    @Override
    public MpLoginOutVo refresh(MpRefreshInVo input){
        String appId = getAppId();
        WxOAuth2AccessToken wxOAuth2AccessToken = new WxOAuth2AccessToken();
        if(StringUtils.isNotBlank(input.getCode())) {
            wxOAuth2AccessToken = getAccessToken(appId,input.getCode());
        }else{
            wxOAuth2AccessToken.setOpenId(jWTUtil.getOpenId());
            wxOAuth2AccessToken.setUnionId(jWTUtil.getUnionId());
            wxOAuth2AccessToken.setAccessToken(jWTUtil.getOauthAccessToken());
        }
        MpLoginOutVo result = setMpLoginOutVo(appId,wxOAuth2AccessToken.getOpenId(),wxOAuth2AccessToken.getUnionId());
        //生成JWT
        JWTToken jwtToken = getJwt(result,wxOAuth2AccessToken.getAccessToken());
        //生成token缓存信息
        loginAsyncTask.setTokenCache(jwtToken);
        if(StringUtils.isNotBlank(input.getCode())) {
            //生成oauthAccessToken缓存信息
            loginAsyncTask.setOAuthAccessTokenCache(appId, wxOAuth2AccessToken.getOpenId(), wxOAuth2AccessToken.getAccessToken(), wxOAuth2AccessToken.getExpiresIn(), wxOAuth2AccessToken.getRefreshToken());
        }
        result.setToken(jwtToken.getToken());
        return result;
    }

    /**
     * 根据网页授权刷新重新获取
     * @param appId
     * @param openId
     * @return
     */
    WxOAuth2AccessToken getAccessToken(String appId,String openId){
        WxOAuth2AccessToken wxOAuth2AccessToken = null;
        String refreshAccessTokenKey = String.format(RedisKeyConstants.Mp.MP_OAUTH_REFRESH_ACCESS_TOKEN,appId.toLowerCase(), openId.toLowerCase());
        if(stringRedisTemplate.hasKey(refreshAccessTokenKey)){
            wxOAuth2AccessToken = getOAuth2RefreshAccessToken(appId,refreshAccessTokenKey);
        }else{
            wxOAuth2AccessToken = getOAuth2AccessToken(appId,openId);
        }
        return wxOAuth2AccessToken;
    }

    /**
     * 调用公众号获取网页授权
     * @param appId
     * @param code
     * @return
     */
    WxOAuth2AccessToken getOAuth2AccessToken(String appId,String code){
        WxOAuth2AccessToken wxOAuth2AccessToken = null;
        try {
            wxOAuth2AccessToken = WxMpUtil.getService(appId).getOAuth2Service().getAccessToken(code);
        } catch (Exception ex) {
            throw new FailException("调用公众号获取网页授权accesstoken接口getOAuth2AccessToken失败:"+ex.getMessage());
        }
        return wxOAuth2AccessToken;
    }

    /**
     * 调用公众号获取网页授权刷新
     * @param appId
     * @param refreshToken
     * @return
     */
    WxOAuth2AccessToken getOAuth2RefreshAccessToken(String appId,String refreshToken){
        WxOAuth2AccessToken wxOAuth2AccessToken = null;
        try {
            wxOAuth2AccessToken = WxMpUtil.getService(appId).getOAuth2Service().refreshAccessToken(refreshToken);
        } catch (Exception ex) {
            throw new FailException("调用公众号获取网页授权刷新accesstoken接口getOAuth2RefreshAccessToken失败:"+ex.getMessage());
        }
        return wxOAuth2AccessToken;
    }

    /**
     * 设置返回内容
     * @param appId
     * @param openId
     * @param unionId
     * @return
     */
    MpLoginOutVo setMpLoginOutVo(String appId,String openId,String unionId){
        MpLoginOutVo mpLoginOutVo = new MpLoginOutVo();
        //查询用户绑定信息
        LambdaQueryWrapper queryWrapper = new LambdaQueryWrapper<MpBindTbl>()
                .eq(MpBindTbl::getAppId,appId)
                .eq(MpBindTbl::getOpenId,openId);
        MpBindTbl mpBindTbl = mpBindTblDao.getOneLimit(queryWrapper);
        if(mpBindTbl==null){
            loginAsyncTask.addMpBind(appId,openId,unionId);
        }else{
            loginAsyncTask.updateMpBindUnionId(appId,openId,unionId);
            mpLoginOutVo.setMemberId(mpBindTbl.getMemberId());
            mpLoginOutVo.setMaBindId(mpBindTbl.getId());
            mpLoginOutVo.setCreateTime(mpBindTbl.getCreateTime());
            mpLoginOutVo.setModifyTime(mpBindTbl.getModifyTime());
            MpUserInfoVo userInfoVo = new MpUserInfoVo();
            userInfoVo.setNickname(mpBindTbl.getNickname());
            userInfoVo.setHeadImgUrl(mpBindTbl.getHeadImgUrl());
            userInfoVo.setGender(mpBindTbl.getGender());
            userInfoVo.setCountry(mpBindTbl.getCountry());
            userInfoVo.setProvince(mpBindTbl.getProvince());
            userInfoVo.setCity(mpBindTbl.getCity());
            userInfoVo.setPrivileges(mpBindTbl.getPrivileges());
            userInfoVo.setSubscribe(mpBindTbl.getSubscribe());
            userInfoVo.setSubscribeTime(mpBindTbl.getSubscribeTime());
            userInfoVo.setSubscribeScene(mpBindTbl.getSubscribeScene());
            userInfoVo.setTagIds(mpBindTbl.getTagIds());
            userInfoVo.setQrScene(mpBindTbl.getQrScene());
            userInfoVo.setQrSceneStr(mpBindTbl.getQrSceneStr());
            mpLoginOutVo.setUserinfo(userInfoVo);
        }
        mpLoginOutVo.setAppId(appId);
        mpLoginOutVo.setOpenId(openId);
        mpLoginOutVo.setUnionId(unionId);
        return mpLoginOutVo;
    }

    /**
     * 生成JWT
     * @param input
     * @param oauthAccessToken
     * @return
     */
    JWTToken getJwt(MpLoginOutVo input,String oauthAccessToken) {
        String token = jWTUtil.DES.encryptHex(jWTUtil.sign(input.getAppId(),input.getOpenId(),input.getUnionId(), input.getMemberId()
                ,input.getMaBindId(),input.getCreateTime(),input.getModifyTime(),input.getUserinfo(),oauthAccessToken));
        return new JWTToken(token, input.getAppId(),input.getOpenId(),mpConstants.getExpireTime());
    }

    /**
     * 公众号退出登录
     */
    @Override
    public void logout(){
        loginAsyncTask.delTokenCache(jWTUtil.getToken());
    }
}
