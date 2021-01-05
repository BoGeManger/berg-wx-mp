package com.berg.mp.controller;

import com.berg.common.controller.AbstractController;
import com.berg.common.constant.Result;
import com.berg.mp.service.mp.LoginService;
import com.berg.vo.mp.in.MpGetAuthUrlInVo;
import com.berg.vo.mp.in.MpLoginInVo;
import com.berg.vo.mp.in.MpRefreshInVo;
import com.berg.vo.mp.out.MpLoginOutVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@Api(tags = "微信公众号用户登录")
public class LoginController extends AbstractController {

    @Autowired
    LoginService loginService;

    @ApiOperation(value = "获取公众号网页授权地址",notes = "前端首次登录或未存在授权信息时需重新调用,并登录获取请求授权")
    @GetMapping(value = "getAuthUrl")
    public Result<String> getAuthUrl(@Validated MpGetAuthUrlInVo input){
        return getSuccessResult("请求成功",loginService.getAuthUrl(input));
    }

    @ApiOperation(value = "公众号登录",notes = "前端未存在请求校验时调用,生成请求校验并返回最新用户信息")
    @PostMapping(value = "login")
    public Result<MpLoginOutVo> login(@RequestBody @Validated MpLoginInVo input){
        return getSuccessResult("请求成功",loginService.login(input));
    }

    @ApiOperation(value = "刷新请求校验",notes = "前端存在请求校验时调用,刷新请求校验并返回最新用户信息")
    @PostMapping(value = "refresh")
    public Result<MpLoginOutVo> refresh(@RequestBody @Validated MpRefreshInVo input){
        return getSuccessResult("请求成功",loginService.refresh(input));
    }

    @ApiOperation(value = "公众号退出登录",notes = "删除缓存请求校验")
    @PostMapping(value = "logout")
    public Result<Boolean> logout(){
        loginService.logout();
        return getSuccessResult("请求成功",true);
    }
}
