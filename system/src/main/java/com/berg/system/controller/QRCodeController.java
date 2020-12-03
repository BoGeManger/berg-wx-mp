package com.berg.system.controller;

import com.berg.base.BaseController;
import com.berg.dao.page.PageInfo;
import com.berg.message.Result;
import com.berg.system.service.mp.QRCodeService;
import com.berg.vo.mp.MpQrCodeTicketVo;
import com.berg.vo.mp.QRCodeVo;
import com.berg.vo.mp.in.GetQRCodePageInVo;
import com.berg.vo.mp.in.MpQRCodeCreateInVo;
import com.berg.vo.mp.in.MpQRCodeCreateTmpInVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/qrcode")
@Api(tags = "微信公众号二维码管理")
public class QRCodeController extends BaseController {

    @Autowired
    QRCodeService qrCodeService;

    @ApiOperation(value = "获取微信二维码分页列表")
    @GetMapping(value = "getQRCodePage")
    public Result<PageInfo<QRCodeVo>> getQRCodePage(@Validated GetQRCodePageInVo input){
        return getSuccessResult("请求成功",qrCodeService.getQRCodePage(input));
    }

    @ApiOperation(value = "创建临时二维码")
    @PostMapping(value = "createTmp")
    public Result<MpQrCodeTicketVo> createTmp(@RequestBody @Validated MpQRCodeCreateTmpInVo input){
        return getSuccessResult("请求成功",qrCodeService.createTmp(input.getAppId(),input.getSceneStr(),input.getExpireSeconds(),input.getRemark()));
    }

    @ApiOperation(value = "创建永久二维码")
    @PostMapping(value = "create")
    public Result<MpQrCodeTicketVo> create(@RequestBody @Validated MpQRCodeCreateInVo input){
        return getSuccessResult("请求成功",qrCodeService.create(input.getAppId(),input.getSceneStr(),input.getRemark()));
    }
}

