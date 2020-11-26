package com.berg.mp.controller;

import com.berg.base.BaseController;
import com.berg.message.Result;
import com.berg.mp.service.mp.QRCodeService;
import com.berg.vo.mp.MpQrCodeTicketVo;
import com.berg.vo.mp.in.MpQRCodeCreateInVo;
import com.berg.vo.mp.in.MpQRCodeCreateTmpInVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/qrcode")
@Api(tags = "微信公众号获取二维码")
public class QRCodeController extends BaseController {

    @Autowired
    QRCodeService qrCodeService;

    @ApiOperation(value = "创建临时二维码")
    @PostMapping(value = "createTmp")
    public Result<MpQrCodeTicketVo> createTmp(@RequestBody @Validated MpQRCodeCreateTmpInVo input){
        return getSuccessResult("请求成功",qrCodeService.createTmp(input.getSceneStr(),input.getExpireSeconds(),input.getRemark()));
    }

    @ApiOperation(value = "创建永久二维码")
    @PostMapping(value = "create")
    public Result<MpQrCodeTicketVo> create(@RequestBody @Validated MpQRCodeCreateInVo input){
        return getSuccessResult("请求成功",qrCodeService.create(input.getSceneStr(),input.getRemark()));
    }
}
