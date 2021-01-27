package com.berg.mp.service.mp.impl;

import com.berg.common.exception.FailException;
import com.berg.auth.mp.service.AbstractService;
import com.berg.mp.service.mp.JsapiService;
import com.berg.vo.mp.out.MpCreateJsapiSignatureOutVo;
import com.berg.wx.utils.WxMpUtil;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class JsapiServiceImpl extends AbstractService implements JsapiService {

    /**
     * 生成JSSDK配置
     * @param url
     * @return
     */
    @Override
    public MpCreateJsapiSignatureOutVo createJsapiSignature(String url){
        String appId = getAppId();
        MpCreateJsapiSignatureOutVo result = new MpCreateJsapiSignatureOutVo();
        try{
            WxJsapiSignature wxJsapiSignature = WxMpUtil.getService(appId).createJsapiSignature(url);
            BeanUtils.copyProperties(wxJsapiSignature,result);
        }catch (Exception ex){
            throw new FailException("调用公众号生产JSSDK签名接口createJsapiSignature失败:"+ex.getMessage());
        }
        return result;
    }
}
