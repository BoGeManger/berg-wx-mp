package com.berg.mp.service.mp;

import com.berg.vo.mp.MpQrCodeTicketVo;

public interface QRCodeService {

    MpQrCodeTicketVo createTmp(String sceneStr, Integer expireSeconds, String remark);

    MpQrCodeTicketVo create(String sceneStr,String remark);
}
