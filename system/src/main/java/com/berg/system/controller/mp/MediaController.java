package com.berg.system.controller.mp;

import com.berg.common.base.BaseController;
import com.berg.dao.page.PageInfo;
import com.berg.common.constant.Result;
import com.berg.system.service.mp.MediaService;
import com.berg.vo.mp.MediaVo;
import com.berg.vo.mp.in.GetMediaPageInVo;
import com.berg.vo.mp.in.MpMaterialDeleteInVo;
import com.berg.vo.mp.in.MpMaterialNewsUpdateInVo;
import com.berg.vo.mp.in.MpMaterialNewsUploadInVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/media")
@Api(tags = "微信公众号素材管理")
public class MediaController extends BaseController {

    @Autowired
    MediaService mediaService;

    @ApiOperation("获取素材分页列表")
    @GetMapping(value = "getMediaPage")
    public Result<PageInfo<MediaVo>> getMediaPage(@Validated GetMediaPageInVo input){
        return getSuccessResult("请求成功",mediaService.getMediaPage(input));
    }


    @ApiOperation("上传临时素材")
    @PostMapping(value = "mediaUpload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result<Boolean> mediaUpload(@ApiParam(value = "文件",required = true) @RequestPart(value = "file") MultipartFile file,
                                         @ApiParam(value = "微信公众号appId",required = true) @RequestParam(value = "appId") String appId,
                                         @ApiParam(value = "素材类型(image 图片 voice 语音 video 视频 thumb 缩略图)",required = true) @RequestParam(value = "mediaType") String mediaType,
                                         @ApiParam(value = "描述") @RequestParam(value = "remark",required = false)String remark){
        mediaService.mediaUpload(appId,mediaType,remark,file);
        return getSuccessResult("请求成功",true);
    }

    @ApiOperation("上传图片永久素材")
    @PostMapping(value = "mediaImgUpload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result<Boolean> mediaImgUpload(@ApiParam(value = "文件",required = true) @RequestPart(value = "file") MultipartFile file,
                                       @ApiParam(value = "微信公众号appId",required = true) @RequestParam(value = "appId") String appId,
                                       @ApiParam(value = "描述") @RequestParam(value = "remark",required = false)String remark){
        mediaService.mediaImgUpload(appId,remark,file);
        return getSuccessResult("请求成功",true);
    }

    @ApiOperation("上传文件永久素材")
    @PostMapping(value = "materialFileUpload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result<Boolean> materialFileUpload(@ApiParam(value = "文件",required = true) @RequestPart(value = "file") MultipartFile file,
                                          @ApiParam(value = "微信公众号appId",required = true) @RequestParam(value = "appId") String appId,
                                          @ApiParam(value = "素材类型(image 图片 voice 语音 video 视频 thumb 缩略图)",required = true) @RequestParam(value = "mediaType") String mediaType,
                                          @ApiParam(value = "名称",required = true) @RequestParam(value = "name")String name,
                                          @ApiParam(value = "媒体标题") @RequestParam(value = "videoTitle",required = false)String videoTitle,
                                          @ApiParam(value = "媒体描述") @RequestParam(value = "videoIntroduction",required = false)String videoIntroduction,
                                          @ApiParam(value = "描述") @RequestParam(value = "remark",required = false)String remark){
        mediaService.materialFileUpload(appId,mediaType,name,videoTitle,videoIntroduction,remark,file);
        return getSuccessResult("请求成功",true);
    }

    @ApiOperation("上传图文永久素材")
    @PostMapping(value = "materialNewsUpload")
    public Result<Boolean> materialNewsUpload(@RequestBody @Validated MpMaterialNewsUploadInVo input){
        mediaService.materialNewsUpload(input);
        return getSuccessResult("请求成功",true);
    }

    @ApiOperation("修改永久素材")
    @PutMapping(value = "materialNewsUpdate")
    public Result<Boolean> materialNewsUpdate(@RequestBody @Validated MpMaterialNewsUpdateInVo input){
        return getSuccessResult("请求成功",mediaService.materialNewsUpdate(input));
    }

    @ApiOperation("删除永久素材")
    @PutMapping(value = "materialDelete")
    public Result<Boolean> materialDelete(@RequestBody @Validated MpMaterialDeleteInVo input){
        return getSuccessResult("请求成功",mediaService.materialDelete(input.getAppId(),input.getMediaId()));
    }
}
