package com.berg.system.controller.mp;

import com.berg.common.base.BaseController;
import com.berg.common.constant.Result;
import com.berg.system.service.mp.DataCubeService;
import com.berg.vo.mp.MpInterfaceSummaryVo;
import com.berg.vo.mp.in.MpGetInterfaceSummaryInVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dataCube")
@Api(tags = "微信公众号数据分析")
public class DataCubeController extends BaseController {

    @Autowired
    DataCubeService dataCubeService;

    @ApiOperation(value = "获取接口分析数据")
    @GetMapping(value = "getInterfaceSummary")
    public Result<List<MpInterfaceSummaryVo>> getInterfaceSummary(@Validated MpGetInterfaceSummaryInVo input){
        return getSuccessResult("请求成功",dataCubeService.getInterfaceSummary(input));
    }

    @ApiOperation(value = "获取接口分析分时数据")
    @GetMapping(value = "getInterfaceSummaryHour")
    public Result<List<MpInterfaceSummaryVo>> getInterfaceSummaryHour(@Validated MpGetInterfaceSummaryInVo input){
        return getSuccessResult("请求成功",dataCubeService.getInterfaceSummaryHour(input));
    }
}
