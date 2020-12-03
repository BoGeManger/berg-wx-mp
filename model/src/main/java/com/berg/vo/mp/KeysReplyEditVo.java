package com.berg.vo.mp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class KeysReplyEditVo {

    @NotNull(message = "微信公众号关键字自动回复id不能为空")
    @Min(value = 0,message = "微信公众号关键字自动回复id不能小于0")
    @ApiModelProperty(value = "微信公众号关键字自动回复id")
    Integer id=0;
    @Size(max = 120, message = "微信公众号appId长度不能超过120个字符")
    @NotBlank(message = "微信公众号appId不能为空")
    @ApiModelProperty(value = "微信公众号appId")
    String appId;
    @Size(max = 120, message = "关键词内容长度不能超过120个字符")
    @NotBlank(message = "关键词内容不能为空")
    @ApiModelProperty(value = "关键词内容")
    String keyContent;
    @Size(max = 255, message = "回复内容长度不能超过255个字符")
    @ApiModelProperty(value = "回复内容")
    String replyContent;
    @Size(max = 255, message = "回复内容html格式长度不能超过255个字符")
    @ApiModelProperty(value = "回复内容html格式")
    String replyContentHtml;
    @Size(max = 120, message = "素材媒体id不能超过120个字符")
    @ApiModelProperty(value = "素材媒体id")
    String mediaId;
    @ApiModelProperty(value = "素材文件类型(image 图片 voice 语音 video 视频 thumb 缩略图)")
    String mediaType;
    @Size(max = 255, message = "图文类型点击跳转链接长度不能超过255个字符")
    @ApiModelProperty(value = "图文类型点击跳转链接")
    String url;
    @Size(max = 255, message = "图文类型封面图片链接长度不能超过255个字符")
    @ApiModelProperty(value = "图文类型封面图片链接")
    String picUrl;
    @NotNull(message = "回复内容类型不能为空")
    @Min(value = 0,message = "回复内容类型不能小于0")
    @ApiModelProperty(value = "回复内容类型(0 文本 1 图文 2 素材)")
    Integer replyType;
    @Size(max = 255, message = "描述长度不能超过255个字符")
    @ApiModelProperty(value = "描述")
    String remark;
}
