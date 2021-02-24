package com.berg.mp.controller;

import com.berg.common.controller.AbstractController;
import com.berg.common.constant.Result;
import com.berg.mp.service.mp.UserService;
import com.berg.vo.mp.in.MpRegisterInVo;
import com.berg.vo.mp.out.MpOAuthUserInfoOutVo;
import com.berg.vo.mp.out.MpUserInfoOutVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/user")
@Api(tags = "微信公众号用户")
public class UserController extends AbstractController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "获取注册验证码")
    @GetMapping(value = "getVerifyCode")
    public Result<Void> getVerifyCode(@NotBlank(message = "手机号码不能为空") @RequestParam(name = "phone", required = true) String phone){
        return success("请求成功",()->userService.getVerifyCode(phone));
    }

    @ApiOperation(value = "用户注册")
    @PostMapping(value = "register")
    public Result<Void> register(@RequestBody @Validated MpRegisterInVo input){
        return success("请求成功",()->userService.register(input));
    }

    @ApiOperation(value = "获取用户基本信息",notes = "用户必须已关注")
    @GetMapping(value = "userInfo")
    public Result<MpUserInfoOutVo> userInfo(){
        return success("请求成功",()->userService.userInfo());
    }

    @ApiOperation(value = "通过网页授权获取用户信息",notes = "用户可未关注，以snsapi_userinfo为scope发起的网页授权")
    @GetMapping(value = "oauthUserInfo")
    public Result<MpOAuthUserInfoOutVo> oauthUserInfo(){
        return success("请求成功",()->userService.oauthUserInfo());
    }
}
