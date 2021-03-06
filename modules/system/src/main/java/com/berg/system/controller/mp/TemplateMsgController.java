package com.berg.system.controller.mp;

import com.berg.common.controller.AbstractController;
import com.berg.dao.page.PageInfo;
import com.berg.common.constant.Result;
import com.berg.system.service.mp.TemplateMsgService;
import com.berg.vo.mp.MpTemplateVo;
import com.berg.vo.mp.MsgRecordVo;
import com.berg.vo.mp.in.GetMsgRecordPageInVo;
import com.berg.vo.mp.in.MpAppIdInVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/template")
@Api(tags = "微信公众号模板消息管理")
public class TemplateMsgController extends AbstractController {

    @Autowired
    TemplateMsgService templateMsgService;

    @ApiOperation("获取模板消息发送记录分页列表")
    @GetMapping(value = "getMsgRecordPage")
    public Result<PageInfo<MsgRecordVo>> getMsgRecordPage(@Validated GetMsgRecordPageInVo input){
        return success("请求成功",()->templateMsgService.getMsgRecordPage(input));
    }

    @ApiOperation(value = "获取模板消息列表")
    @GetMapping(value = "getTemplateList")
    public Result<List<MpTemplateVo>> getTemplateList(@ApiParam(value = "微信公众号appId",required = true) @RequestParam String appId){
        return success("请求成功",()->templateMsgService.getTemplateList(appId));
    }

    @ApiOperation(value = "删除模板缓存")
    @DeleteMapping(value = "delTemplateCache")
    public Result<Void> delTemplateCache(@RequestBody @Validated MpAppIdInVo input){
        return success("请求成功",()->templateMsgService.delTemplateCache(input.getAppId()));
    }
}
