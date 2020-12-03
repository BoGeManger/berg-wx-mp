package com.berg.system.service.mp.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.berg.dao.page.PageInfo;
import com.berg.dao.system.mp.entity.QrcodeTbl;
import com.berg.dao.system.mp.service.QrcodeTblDao;
import com.berg.exception.FailException;
import com.berg.system.service.mp.QRCodeService;
import com.berg.vo.mp.MpQrCodeTicketVo;
import com.berg.vo.mp.QRCodeVo;
import com.berg.vo.mp.in.GetQRCodePageInVo;
import com.berg.wx.mp.utils.WxMpUtil;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QRCodeServiceImpl implements QRCodeService {

    @Autowired
    QrcodeTblDao qrcodeTblDao;

    /**
     * 获取微信二维码分页列表
     * @param input
     * @return
     */
    public PageInfo<QRCodeVo> getQRCodePage(GetQRCodePageInVo input){
        return qrcodeTblDao.page(input,()->{
            LambdaQueryWrapper quary = new LambdaQueryWrapper<QrcodeTbl>()
                    .eq(QrcodeTbl::getAppId,input.getAppId())
                    .eq(StringUtils.isNotBlank(input.getSceneStr()),QrcodeTbl::getSceneStr,input.getSceneStr())
                    .eq(StringUtils.isNotBlank(input.getRemark()),QrcodeTbl::getRemark,input.getRemark());
           return qrcodeTblDao.list(quary,QRCodeVo.class);
        });
    }

    @Autowired
    QRCodeAsyncTask qrCodeAsyncTask;

    /**
     * 创建临时二维码
     * @param expireSeconds
     * @return
     */
    @Override
    public MpQrCodeTicketVo createTmp(String appId,String sceneStr, Integer expireSeconds, String remark){
        MpQrCodeTicketVo reuslt = new MpQrCodeTicketVo();
        try{
            WxMpQrCodeTicket wxMpQrCodeTicket = WxMpUtil.getService(appId).getQrcodeService().qrCodeCreateTmpTicket(sceneStr,expireSeconds);
            BeanUtils.copyProperties(wxMpQrCodeTicket,reuslt);
            qrCodeAsyncTask.addQRCode(appId,sceneStr,expireSeconds,reuslt.getTicket(),reuslt.getUrl(),remark,0);
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
    public MpQrCodeTicketVo create(String appId,String sceneStr,String remark){
        MpQrCodeTicketVo reuslt = new MpQrCodeTicketVo();
        try{
            WxMpQrCodeTicket wxMpQrCodeTicket = WxMpUtil.getService(appId).getQrcodeService().qrCodeCreateLastTicket(sceneStr);
            BeanUtils.copyProperties(wxMpQrCodeTicket,reuslt);
            qrCodeAsyncTask.addQRCode(appId,sceneStr,0,reuslt.getTicket(),reuslt.getUrl(),remark,1);
        }catch (Exception ex){
            throw new FailException("调用公众号创建永久二维码接口create失败:"+ex.getMessage());
        }
        return reuslt;
    }
}
