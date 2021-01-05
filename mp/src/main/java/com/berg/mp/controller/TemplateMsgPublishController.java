package com.berg.mp.controller;

import com.berg.common.controller.AbstractController;
import com.berg.dao.page.PageInfo;
import com.berg.common.constant.Result;
import com.berg.mp.service.mp.TemplateMsgPublishService;
import com.berg.vo.common.PageInVo;
import com.berg.vo.mp.MsgPublishVo;
import com.berg.vo.mp.in.GetMsgPublishPageInVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/templateMsgPublish")
@Api(tags = "微信公众号模板消息发布")
public class TemplateMsgPublishController extends AbstractController {

    @Autowired
    TemplateMsgPublishService templateMsgPublishService;

    @ApiOperation("获取模板消息发布分页列表")
    @GetMapping(value = "getMsgPublishPage")
    public Result<PageInfo<MsgPublishVo>> getMsgPublishPage(@Validated GetMsgPublishPageInVo input){
        return getSuccessResult("请求成功",templateMsgPublishService.getMsgPublishPage(input));
    }

    @ApiOperation("获取已订阅模板消息分页列表")
    @GetMapping(value = "getMsgSubscribePublishPage")
    public Result<PageInfo<MsgPublishVo>> getMsgSubscribePublishPage(@Validated PageInVo input){
        return getSuccessResult("请求成功",templateMsgPublishService.getMsgSubscribePublishPage(input));
    }
}
