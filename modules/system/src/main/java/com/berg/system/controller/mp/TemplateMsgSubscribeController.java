package com.berg.system.controller.mp;

import com.berg.common.controller.AbstractController;
import com.berg.dao.page.PageInfo;
import com.berg.common.constant.Result;
import com.berg.system.service.mp.TemplateMsgSubscribeService;
import com.berg.vo.mp.MsgSubscribeVo;
import com.berg.vo.mp.in.GetMsgSubscribePageInVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/templateMsgSubscribe")
@Api(tags = "微信公众号模板消息订阅管理")
public class TemplateMsgSubscribeController extends AbstractController {

    @Autowired
    TemplateMsgSubscribeService templateMsgSubscribeService;

    @ApiOperation("获取模板消息订阅分页列表")
    @GetMapping(value = "getMsgSubscribePage")
    public Result<PageInfo<MsgSubscribeVo>> getMsgSubscribePage(@Validated GetMsgSubscribePageInVo input){
        return success("请求成功",()->templateMsgSubscribeService.getMsgSubscribePage(input));
    }
}
