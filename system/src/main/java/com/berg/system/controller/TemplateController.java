package com.berg.system.controller;

import com.berg.base.BaseController;
import com.berg.message.Result;
import com.berg.system.service.mp.TemplateService;
import com.berg.vo.mp.MpTemplateVo;
import com.berg.vo.mp.in.MpAppIdInVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Api(tags = "用户管理")
public class TemplateController extends BaseController {

    @Autowired
    TemplateService templateService;

    @ApiOperation(value = "获取模板消息列表")
    @GetMapping(value = "getTemplateList")
    public Result<List<MpTemplateVo>> getTemplateList(@ApiParam(value = "微信公众号appId",required = true) @RequestParam String appId){
        return getSuccessResult("请求成功",templateService.getTemplateList(appId));
    }

    @ApiOperation(value = "删除模板缓存")
    @DeleteMapping(value = "delTemplateCache")
    public Result<Boolean> delTemplateCache(@RequestBody @Validated MpAppIdInVo input){
        templateService.delTemplateCache(input.getAppId());
        return getSuccessResult("请求成功",true);
    }
}
