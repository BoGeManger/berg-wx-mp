package com.berg.mp.controller;

import com.berg.common.controller.AbstractController;
import com.berg.common.constant.Result;
import com.berg.mp.service.mp.TemplateMsgSubscribeService;
import com.berg.vo.common.EntityIdVo;
import com.berg.vo.mp.in.TemplateSubscribeInVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/templateMsgSubscribe")
@Api(tags = "微信公众号模板消息订阅")
public class TemplateMsgSubscribeController extends AbstractController {

    @Autowired
    TemplateMsgSubscribeService templateMsgSubscribeService;

    @ApiOperation("订阅模板消息")
    @PostMapping(value = "subscribe")
    public Result<Boolean> subscribe(@RequestBody @Validated TemplateSubscribeInVo input){
        templateMsgSubscribeService.subscribe(input);
        return getSuccessResult("请求成功",true);
    }

    @ApiOperation(value = "取消订阅模板消息",notes = "id为模板消息发布id")
    @PostMapping(value = "unsubscribe")
    public Result<Boolean> unsubscribe(@RequestBody @Validated EntityIdVo<String> input){
        templateMsgSubscribeService.unsubscribe(input.getId());
        return getSuccessResult("请求成功",true);
    }
}
