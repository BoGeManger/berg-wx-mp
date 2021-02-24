package com.berg.system.controller.mp;

import com.berg.common.controller.AbstractController;
import com.berg.common.constant.Result;
import com.berg.system.service.mp.NetworkService;
import com.berg.vo.mp.in.MpNetCheckInVo;
import com.berg.vo.mp.out.MpNetCheckOutVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/network")
@Api(tags = "微信公众号网络管理")
public class NetworkController extends AbstractController {

    @Autowired
    NetworkService networkService;

    @ApiOperation(value = "网络检测")
    @GetMapping(value = "netCheck")
    public Result<MpNetCheckOutVo> netCheck(@Validated MpNetCheckInVo input){
        return success("请求成功",()->networkService.netCheck(input));
    }
}
