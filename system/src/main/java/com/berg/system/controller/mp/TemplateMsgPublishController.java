package com.berg.system.controller.mp;

import com.berg.common.base.BaseController;
import com.berg.dao.page.PageInfo;
import com.berg.common.constant.Result;
import com.berg.system.service.mp.TemplateMsgPublishService;
import com.berg.vo.common.EntityIdVo;
import com.berg.vo.mp.MsgPublishEditVo;
import com.berg.vo.mp.MsgPublishVo;
import com.berg.vo.mp.in.GetMsgPublishPageInVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/templateMsgPublish")
@Api(tags = "微信公众号模板消息发布管理")
public class TemplateMsgPublishController extends BaseController {

    @Autowired
    TemplateMsgPublishService templateMsgPublishService;

    @ApiOperation("获取模板消息发布分页列表")
    @GetMapping(value = "getMsgPublishPage")
    public Result<PageInfo<MsgPublishVo>> getMsgPublishPage(@Validated GetMsgPublishPageInVo input){
        return getSuccessResult("请求成功",templateMsgPublishService.getMsgPublishPage(input));
    }

    @ApiOperation("获取模板消息发布")
    @GetMapping(value = "getMsgPublish")
    public Result<MsgPublishEditVo> getMsgPublish(@ApiParam(value = "表id",required = true) @RequestParam String id){
        return getSuccessResult("请求成功",templateMsgPublishService.getMsgPublish(id));
    }

    @ApiOperation("新增模板消息发布")
    @PostMapping(value = "addMsgPublish")
    public Result<MsgPublishEditVo> addMsgPublish(@RequestBody @Validated MsgPublishEditVo input){
        return getSuccessResult("请求成功",templateMsgPublishService.addMsgPublish(input));
    }

    @ApiOperation("修改模板消息发布")
    @PutMapping(value = "updateMsgPublish")
    public Result<MsgPublishEditVo> updateMsgPublish(@RequestBody @Validated MsgPublishEditVo input){
        return getSuccessResult("请求成功",templateMsgPublishService.updateMsgPublish(input));
    }

    @ApiOperation("立即发送消息")
    @PutMapping(value = "sendMessage")
    public Result<Boolean> sendMessage(@RequestBody @Validated EntityIdVo<String> input){
        templateMsgPublishService.sendMessage(input.getId());
        return getSuccessResult("请求成功",true);
    }
}
