package com.berg.mp.service.mp.impl;

import com.berg.exception.FailException;
import com.berg.mp.service.base.BaseService;
import com.berg.mp.service.mp.QRCodeService;
import com.berg.vo.mp.MpQrCodeTicketVo;
import com.berg.wx.mp.utils.WxMpUtil;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QRCodeServiceImpl extends BaseService implements QRCodeService {

    @Autowired
    QRCodeAsyncTask qrCodeAsyncTask;

    /**
     * 创建临时二维码
     * @param expireSeconds
     * @return
     */
    @Override
    public MpQrCodeTicketVo createTmp(String sceneStr,Integer expireSeconds,String remark){
        String appId = getAppId();
        String openId = jWTUtil.getOpenId();
        MpQrCodeTicketVo reuslt = new MpQrCodeTicketVo();
        try{
            WxMpQrCodeTicket wxMpQrCodeTicket = WxMpUtil.getService(appId).getQrcodeService().qrCodeCreateTmpTicket(sceneStr,expireSeconds);
            BeanUtils.copyProperties(wxMpQrCodeTicket,reuslt);
            qrCodeAsyncTask.addQRCode(appId,openId,sceneStr,expireSeconds,reuslt.getTicket(),reuslt.getUrl(),remark,0);
        }catch (Exception ex){
            throw new FailException("调用公众号创建临时二维码接口createTmp失败:"+ex.getMessage());
        }
        return reuslt;
    }

    /**
     * 创建永久二维码
     * @param remark
     * @return
     */
    @Override
    public MpQrCodeTicketVo create(String sceneStr,String remark){
        MpQrCodeTicketVo reuslt = new MpQrCodeTicketVo();
        String appId = getAppId();
        String openId = jWTUtil.getOpenId();
        try{
            WxMpQrCodeTicket wxMpQrCodeTicket = WxMpUtil.getService(appId).getQrcodeService().qrCodeCreateLastTicket(sceneStr);
            BeanUtils.copyProperties(wxMpQrCodeTicket,reuslt);
            qrCodeAsyncTask.addQRCode(appId,openId,sceneStr,0,reuslt.getTicket(),reuslt.getUrl(),remark,1);
        }catch (Exception ex){
            throw new FailException("调用公众号创建永久二维码接口create失败:"+ex.getMessage());
        }
        return reuslt;
    }
}
