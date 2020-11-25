package com.berg.mp.controller;

import com.berg.base.BaseController;
import com.berg.message.Result;
import com.berg.mp.service.mp.KefuService;
import com.berg.vo.mp.in.MpKefuSendTextInVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kefu")
@Api(tags = "微信公众号客服消息")
public class KefuController extends BaseController {

    @Autowired
    KefuService kefuService;

    @ApiOperation(value = "发送客服文本消息")
    @PostMapping(value = "sendText")
    public Result<Boolean> sendText(@RequestBody @Validated MpKefuSendTextInVo input){
        return getSuccessResult("请求成功",kefuService.sendText(input));
    }
}
