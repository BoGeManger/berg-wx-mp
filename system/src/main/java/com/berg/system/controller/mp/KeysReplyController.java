package com.berg.system.controller.mp;

import com.berg.common.base.BaseController;
import com.berg.dao.page.PageInfo;
import com.berg.common.constant.Result;
import com.berg.system.service.mp.KeysReplyService;
import com.berg.vo.common.EntityIdVo;
import com.berg.vo.mp.KeysReplyEditVo;
import com.berg.vo.mp.KeysReplyVo;
import com.berg.vo.mp.in.GetKeysReplyPageInVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/keysReply")
@Api(tags = "微信公众号关键字自动回复管理")
public class KeysReplyController extends BaseController {

    @Autowired
    KeysReplyService keysReplyService;

    @ApiOperation("获取关键字自动回复分页列表")
    @GetMapping(value = "getKeysReplyPage")
    public Result<PageInfo<KeysReplyVo>> getKeysReplyPage(@Validated GetKeysReplyPageInVo input){
        return getSuccessResult("请求成功",keysReplyService.getKeysReplyPage(input));
    }

    @ApiOperation("获取关键字自动回复")
    @GetMapping(value = "getKeysReply")
    public Result<KeysReplyEditVo> getKeysReply(@ApiParam(value = "表id",required = true) @RequestParam Integer id){
        return getSuccessResult("请求成功",keysReplyService.getKeysReply(id));
    }

    @ApiOperation("新增关键字自动回复")
    @PostMapping(value = "addKeysReply")
    public Result<Integer> addKeysReply(@RequestBody @Validated KeysReplyEditVo input){
        return getSuccessResult("请求成功",keysReplyService.addKeysReply(input));
    }

    @ApiOperation("修改关键字自动回复")
    @PutMapping(value = "updateKeysReply")
    public Result<Integer> updateKeysReply(@RequestBody @Validated KeysReplyEditVo input){
        return getSuccessResult("请求成功",keysReplyService.updateKeysReply(input));
    }

    @ApiOperation("删除关键字自动回复")
    @DeleteMapping(value = "delKeysReply")
    public Result<Boolean> delKeysReply(@RequestBody EntityIdVo<Integer> input){
        keysReplyService.delKeysReply(input.getId());
        return getSuccessResult("请求成功",true);
    }
}
