package com.berg.system.service.mp;

import com.berg.dao.page.PageInfo;
import com.berg.vo.mp.MpQrCodeTicketVo;
import com.berg.vo.mp.QRCodeVo;
import com.berg.vo.mp.in.GetQRCodePageInVo;

public interface QRCodeService {

    PageInfo<QRCodeVo> getQRCodePage(GetQRCodePageInVo input);

    MpQrCodeTicketVo createTmp(String appId,String sceneStr, Integer expireSeconds, String remark);

    MpQrCodeTicketVo create(String appId,String sceneStr,String remark);
}
