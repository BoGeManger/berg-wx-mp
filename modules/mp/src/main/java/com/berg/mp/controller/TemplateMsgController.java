package com.berg.mp.controller;

import com.berg.common.controller.AbstractController;
import com.berg.common.constant.Result;
import com.berg.mp.service.mp.TemplateMsgService;
import com.berg.vo.mp.MpTemplateVo;
import com.berg.vo.mp.in.MpTemplateSendInVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/templateMsg")
@Api(tags = "微信公众号模板消息")
public class TemplateMsgController extends AbstractController {

    @Autowired
    TemplateMsgService templateMsgService;

    @ApiOperation(value = "获取模板消息列表")
    @GetMapping(value = "getTemplateList")
    public Result<List<MpTemplateVo>> getTemplateList(){
        return success("请求成功",()->templateMsgService.getTemplateList());
    }

    @ApiOperation(value = "发送模板消息",notes = "返回微信消息id")
    @PostMapping(value = "send")
    public Result<String> send(@RequestBody @Validated MpTemplateSendInVo input){
        return success("请求成功",()->templateMsgService.send(input));
    }
}
