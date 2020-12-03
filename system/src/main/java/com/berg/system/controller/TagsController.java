package com.berg.system.controller;

import com.berg.base.BaseController;
import com.berg.message.Result;
import com.berg.system.service.mp.TagsService;
import com.berg.vo.mp.MpUserTagVo;
import com.berg.vo.mp.in.MpBatchTagInVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
@Api(tags = "微信公众号标签管理")
public class TagsController extends BaseController {

    @Autowired
    TagsService tagsService;

    @ApiOperation("获取标签列表")
    @GetMapping(value = "getTagsList")
    public Result<List<MpUserTagVo>> getTagsList(@ApiParam(value = "微信公众号appId",required = true) @RequestParam String appId){
        return getSuccessResult("请求成功",tagsService.getTagsList(appId));
    }

    @ApiOperation("批量为用户打标签")
    @PostMapping(value = "batchTagging")
    public Result<Boolean> batchTagging(@RequestBody @Validated MpBatchTagInVo input){
        return getSuccessResult("请求成功",tagsService.batchTagging(input));
    }

    @ApiOperation("批量为用户取消标签")
    @PostMapping(value = "batchUntagging")
    public Result<Boolean> batchUntagging(@RequestBody @Validated MpBatchTagInVo input){
        return getSuccessResult("请求成功",tagsService.batchUntagging(input));
    }
}
