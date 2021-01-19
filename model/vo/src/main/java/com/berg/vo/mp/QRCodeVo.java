package com.berg.vo.mp;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class QRCodeVo {

    @ApiModelProperty(value = "微信公众号二维码表id")
    Integer id;
    @ApiModelProperty(value = "场景id")
    String sceneStr;
    @ApiModelProperty(value = "该二维码有效时间，以秒为单位。 最大不超过2592000（即30天）")
    Integer expireSeconds;
    @ApiModelProperty(value = "二维码图片解析后的地址")
    String url;
    @ApiModelProperty(value = "描述")
    String remark;
    @ApiModelProperty(value = "类型(0 临时二维码 1 永久二维码)")
    Integer type;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    LocalDateTime createTime;
}
