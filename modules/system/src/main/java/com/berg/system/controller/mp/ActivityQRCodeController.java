package com.berg.system.controller.mp;

import com.berg.common.controller.AbstractController;
import com.berg.dao.page.PageInfo;
import com.berg.common.constant.Result;
import com.berg.system.service.mp.ActivityQRCodeService;
import com.berg.vo.common.EntityIdVo;
import com.berg.vo.mp.ActivityQRCodeEditVo;
import com.berg.vo.mp.ActivityQRCodeRecordStatisticsVo;
import com.berg.vo.mp.ActivityQRCodeVo;
import com.berg.vo.mp.in.GetActivityQRCodePageInVo;
import com.berg.vo.mp.in.GetActivityRecordStatisticsPageInVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/keysReply")
@Api(tags = "微信公众号活动二维码管理")
public class ActivityQRCodeController extends AbstractController {

    @Autowired
    ActivityQRCodeService activityQRCodeService;

    @ApiOperation("获取活动二维码分页列表")
    @GetMapping(value = "getActivityQRCodePage")
    public Result<PageInfo<ActivityQRCodeVo>> getActivityQRCodePage(@Validated GetActivityQRCodePageInVo input){
        return success("请求成功",()->activityQRCodeService.getActivityQRCodePage(input));
    }

    @ApiOperation("获取活动二维码")
    @GetMapping(value = "getActivityQRCode")
    public Result<ActivityQRCodeEditVo> getActivityQRCode(@ApiParam(value = "表id",required = true) @RequestParam String id){
        return success("请求成功",()->activityQRCodeService.getActivityQRCode(id));
    }

    @ApiOperation("新增关键字自动回复")
    @PostMapping(value = "addOrUpdateActivityQRCode")
    public Result<String> addOrUpdateActivityQRCode(@RequestBody @Validated ActivityQRCodeEditVo input){
        return success("请求成功",()->activityQRCodeService.addOrUpdateActivityQRCode(input));
    }

    @ApiOperation("修改活动二维码")
    @PutMapping(value = "updateActivityQRCode")
    public Result<String> updateActivityQRCode(@RequestBody @Validated ActivityQRCodeEditVo input){
        return success("请求成功",()->activityQRCodeService.updateActivityQRCode(input));
    }

    @ApiOperation("删除活动二维码")
    @DeleteMapping(value = "delActivityQRCode")
    public Result<Void> delActivityQRCode(@RequestBody EntityIdVo<String> input){
        return success("请求成功",()->activityQRCodeService.delActivityQRCode(input.getId()));
    }

    @ApiOperation("获取活动二维码记录统计分页列表")
    @GetMapping(value = "getActivityRecordStatisticsPage")
    public Result<PageInfo<ActivityQRCodeRecordStatisticsVo>> getActivityRecordStatisticsPage(@Validated GetActivityRecordStatisticsPageInVo input){
        return success("请求成功",()->activityQRCodeService.getActivityRecordStatisticsPage(input));
    }
}
