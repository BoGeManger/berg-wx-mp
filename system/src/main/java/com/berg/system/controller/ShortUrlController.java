package com.berg.system.controller;

import com.berg.base.BaseController;
import com.berg.dao.page.PageInfo;
import com.berg.message.Result;
import com.berg.system.service.mp.ShortUrlService;
import com.berg.vo.mp.ShortUrlVo;
import com.berg.vo.mp.in.GetShortUrlPageInVo;
import com.berg.vo.mp.in.MpCreateShortUrlInVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shortUrl")
@Api(tags = "微信公众号短连接管理")
public class ShortUrlController extends BaseController {

    @Autowired
    ShortUrlService shortUrlService;

    @ApiOperation(value = "获取短连接记录分页列表")
    @GetMapping(value = "getShortUrlPage")
    public Result<PageInfo<ShortUrlVo>> getShortUrlPage(@Validated GetShortUrlPageInVo input){
        return getSuccessResult("请求成功",shortUrlService.getShortUrlPage(input));
    }

    @ApiOperation(value = "生成短连接")
    @PostMapping(value = "create")
    public Result<String> create(@RequestBody @Validated MpCreateShortUrlInVo input){
        return getSuccessResult("请求成功",shortUrlService.create(input));
    }
}
