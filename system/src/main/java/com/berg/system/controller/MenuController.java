package com.berg.system.controller;

import com.berg.common.base.BaseController;
import com.berg.dao.page.PageInfo;
import com.berg.common.constant.Result;
import com.berg.system.service.mp.MenuService;
import com.berg.vo.mp.MenuVo;
import com.berg.vo.mp.MpMenuVo;
import com.berg.vo.mp.in.GetMenuPageInVo;
import com.berg.vo.mp.in.MpAppIdInVo;
import com.berg.vo.mp.in.MpCreateMenuInVo;
import com.berg.vo.mp.out.MpGetMenuOutVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/network")
@Api(tags = "微信公众号菜单管理")
public class MenuController extends BaseController {

    @Autowired
    MenuService menuService;

    @ApiOperation(value = "获取菜单记录分页列表")
    @GetMapping(value = "getMenuPage")
    public Result<PageInfo<MenuVo>> getMenuPage(@Validated GetMenuPageInVo input){
        return getSuccessResult("请求成功",menuService.getMenuPage(input));
    }

    @ApiOperation(value = "获取菜单记录明细")
    @GetMapping(value = "find")
    public Result<MpMenuVo> find(@ApiParam(value = "表id",required = true) @RequestParam Integer id){
        return getSuccessResult("请求成功",menuService.find(id));
    }

    @ApiOperation(value = "获取公众号菜单")
    @GetMapping(value = "get")
    public Result<MpGetMenuOutVo> get(@ApiParam(value = "微信公众号appId",required = true) @RequestParam String appId){
        return getSuccessResult("请求成功",menuService.get(appId));
    }

    @ApiOperation(value = "新增公众号菜单")
    @PostMapping(value = "create")
    public Result<Boolean> create(@RequestBody @Validated MpCreateMenuInVo input){
        menuService.create(input);
        return getSuccessResult("请求成功",true);
    }

    @ApiOperation(value = "删除公众号菜单")
    @DeleteMapping(value = "delete")
    public Result<Boolean> delete(@RequestBody @Validated MpAppIdInVo input){
        menuService.delete(input.getAppId());
        return getSuccessResult("请求成功",true);
    }
}
