package com.berg.mp.controller;

import com.berg.common.controller.AbstractController;
import com.berg.common.constant.Result;
import com.berg.mp.service.mp.JsapiService;
import com.berg.vo.mp.out.MpCreateJsapiSignatureOutVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/jsapi")
@Api(tags = "微信公众号JSSDK")
public class JsapiController extends AbstractController {

    @Autowired
    JsapiService jsapiService;

    @ApiOperation(value = "生成JSSDK配置")
    @GetMapping(value = "createJsapiSignature")
    public Result<MpCreateJsapiSignatureOutVo> createJsapiSignature(@NotBlank(message = "请求地址不能为空") @RequestParam(name = "url", required = true) String url){
        return success("请求成功",()->jsapiService.createJsapiSignature(url));
    }
}
