package com.berg.vo.mp;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MediaVo {

    @ApiModelProperty(value = "微信公众号媒体表id")
    Integer id;
    @ApiModelProperty(value = "微信公众号appId")
    String appId;
    @ApiModelProperty(value = "素材媒体id")
    String mediaId;
    @ApiModelProperty(value = "素材链接")
    String url;
    @ApiModelProperty(value = "媒体文件上传时间戳")
    Integer createdAt;
    @ApiModelProperty(value = "素材文件类型(image 图片 voice 语音 video 视频 thumb 缩略图)")
    String fileType;
    @ApiModelProperty(value = "素材类型(0 临时素材 1 永久素材)")
    Integer type;
    @ApiModelProperty(value = "描述")
    String remark;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    LocalDateTime createTime;
    @ApiModelProperty(value = "创建用户")
    String createUser;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间")
    LocalDateTime modifyTime;
    @ApiModelProperty(value = "修改用户")
    String modifyUser;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "删除时间")
    LocalDateTime delTime;
    @ApiModelProperty(value = "删除用户")
    String delUser;
    @ApiModelProperty(value = "是否删除(0 否,1 是)")
    Integer isdel;
}
