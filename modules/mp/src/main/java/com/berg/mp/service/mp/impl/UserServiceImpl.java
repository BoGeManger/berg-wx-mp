package com.berg.mp.service.mp.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.berg.common.constant.RedisKeyConstants;
import com.berg.dao.system.mb.entity.MemberTbl;
import com.berg.dao.system.mb.entity.MpBindTbl;
import com.berg.dao.system.mb.service.MemberTblDao;
import com.berg.dao.system.mb.service.MpBindTblDao;
import com.berg.common.exception.FailException;
import com.berg.common.exception.UnauthException;
import com.berg.common.exception.UserFriendException;
import com.berg.auth.mp.service.AbstractService;
import com.berg.mp.service.mp.UserService;
import com.berg.vo.mp.in.MpRegisterInVo;
import com.berg.vo.mp.out.MpOAuthUserInfoOutVo;
import com.berg.vo.mp.out.MpUserInfoOutVo;
import com.berg.wx.utils.WxMpUtil;
import me.chanjar.weixin.common.bean.WxOAuth2UserInfo;
import me.chanjar.weixin.common.bean.oauth2.WxOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl extends AbstractService implements UserService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    UserAsyncTask userAsyncTask;

    @Autowired
    MpBindTblDao mpBindTblDao;
    @Autowired
    MemberTblDao memberTblDao;

    /**
     * 获取注册验证码
     * @param phone
     */
    @Override
    public void getVerifyCode(String phone){
        String appId = getAppId();
        //TODO 请求短信平台生成验证码
        String code = "11111";
        //生成验证码缓存
        String key = String.format(RedisKeyConstants.Mp.MP_VERIFY_CODE,appId.toLowerCase(),phone);
        stringRedisTemplate.opsForValue().set(key,code,60, TimeUnit.SECONDS);
    }

    /**
     * 用户注册
     * @param input
     */
    @Override
    public void register(MpRegisterInVo input){
        String appId = getAppId();
        String openId= getOpenId();
        //校验验证码是否过期
        String key = String.format(RedisKeyConstants.Mp.MP_VERIFY_CODE,appId.toLowerCase(),input.getPhone());
        String verifyCode = stringRedisTemplate.opsForValue().get(key);
        if(!verifyCode.equals(input.getCode())){
            throw new UserFriendException("验证码已过期，请重新获取");
        }
        //注册会员更新绑定会员id
        addMemberAndUpdateBindMp(appId,openId,input.getPhone());
    }

    /**
     * 新增会员并更新绑定会员id
     * @param appId
     * @param openId
     * @param phone
     */
    void addMemberAndUpdateBindMp(String appId,String openId,String phone){
        MemberTbl memberTbl =memberTblDao.getOneLimit(new LambdaQueryWrapper<MemberTbl>().eq(MemberTbl::getPhone,phone));
        if(memberTbl==null){
            memberTbl = new MemberTbl();
            LocalDateTime now =LocalDateTime.now();
            memberTbl.setId(UUID.randomUUID().toString());
            memberTbl.setPhone(phone);
            memberTbl.setCreateTime(now);
            memberTbl.setModifyTime(now);
            memberTblDao.save(memberTbl);

            LambdaQueryWrapper query = new LambdaQueryWrapper<MpBindTbl>()
                    .eq(MpBindTbl::getAppId, appId)
                    .eq(MpBindTbl::getOpenId, openId);
            MpBindTbl mpBindTbl = new MpBindTbl();
            mpBindTbl.setMemberId(memberTbl.getId());
            mpBindTbl.setModifyTime(now);
            mpBindTblDao.update(mpBindTbl, query);
        }
    }

    /**
     * 获取用户信息
     * @return
     */
    @Override
    public MpUserInfoOutVo userInfo(){
        MpUserInfoOutVo result = new MpUserInfoOutVo();
        String appId = getAppId();
        String openId = getOpenId();
        try{
            WxMpUser wxMpUser = WxMpUtil.getService(appId).getUserService().userInfo(openId);
            BeanUtils.copyProperties(wxMpUser,result);
            userAsyncTask.updateMaBindByUserInfo(appId,openId,result);
        }catch (Exception ex){
            throw new FailException("调用获取用户信息接口userInfo失败:"+ex.getMessage());
        }
        return result;
    }

    /**
     * 通过网页授权获取用户信息
     * @return
     */
    @Override
    public MpOAuthUserInfoOutVo oauthUserInfo(){
        MpOAuthUserInfoOutVo result = new MpOAuthUserInfoOutVo();
        String appId = getAppId();
        String openId = getOpenId();
        String key = String.format(RedisKeyConstants.Mp.MP_OAUTH_ACCESS_TOKEN,appId.toLowerCase(), openId.toLowerCase());
        String accessToken = stringRedisTemplate.opsForValue().get(key);
        if(!StringUtils.isNotBlank(accessToken)){
            throw new UnauthException("网页授权已过期，请携带code重新调用refresh");
        }
        try{
            WxOAuth2AccessToken wxOAuth2AccessToken = new WxOAuth2AccessToken();
            wxOAuth2AccessToken.setAccessToken(accessToken);
            wxOAuth2AccessToken.setOpenId(openId);
            WxOAuth2UserInfo wxOAuth2UserInfo = WxMpUtil.getService(appId).getOAuth2Service().getUserInfo(wxOAuth2AccessToken,null);
            BeanUtils.copyProperties(wxOAuth2UserInfo,result);
            userAsyncTask.updateMaBindByOAuthUserInfo(appId,openId,result);
        }catch (Exception ex){
            throw new FailException("调用通过网页授权获取用户信息接口oauthUserInfo失败:"+ex.getMessage());
        }
        return result;
    }
}
