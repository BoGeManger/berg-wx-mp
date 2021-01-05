package com.berg.system.controller.mp;

import com.berg.common.controller.AbstractController;
import com.berg.common.constant.Result;
import com.berg.system.service.mp.AppService;
import com.berg.vo.mp.MpAppVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mpapp")
@Api(tags = "微信公众号应用管理")
public class MpAppController extends AbstractController {

    @Autowired
    AppService appService;

    @ApiOperation("获取公众号应用列表")
    @GetMapping(value = "getAppList")
    public Result<List<MpAppVo>> getAppList(){
        return getSuccessResult("请求成功",appService.getAppList());
    }
}
