package com.berg.mp.controller;

import com.berg.common.controller.AbstractController;
import com.berg.common.constant.Result;
import com.berg.mp.service.mp.PortalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/portal/{appId}")
@Api(tags = "微信公众号消息")
public class PortalController extends AbstractController {

    @Autowired
    PortalService portalService;

    @ApiOperation(value = "接收微信公众号认证消息")
    @GetMapping(value = "authentication",produces = "text/plain;charset=utf-8")
    public Result<Boolean> authentication(@PathVariable String appId,
                               @RequestParam(name = "signature", required = false) String signature,
                               @RequestParam(name = "timestamp", required = false) String timestamp,
                               @RequestParam(name = "nonce", required = false) String nonce,
                               @RequestParam(name = "echostr", required = false) String echostr){
        portalService.authentication(appId,signature,timestamp,nonce,echostr);
        return getSuccessResult("请求成功",true);
    }

    @ApiOperation(value = "接收微信公众号消息")
    @PostMapping(value = "handler",produces = "application/xml; charset=UTF-8")
    public Result<Boolean> handler(@PathVariable String appId,
                       @RequestBody String requestBody,
                       @RequestParam("signature") String signature,
                       @RequestParam("timestamp") String timestamp,
                       @RequestParam("nonce") String nonce,
                       @RequestParam("openid") String openId,
                       @RequestParam(name = "encrypt_type", required = false) String encType,
                       @RequestParam(name = "msg_signature", required = false) String msgSignature) {
        portalService.handler(appId,signature,timestamp,nonce,openId,encType,msgSignature,requestBody);
        return getSuccessResult("请求成功",true);
    }
}
