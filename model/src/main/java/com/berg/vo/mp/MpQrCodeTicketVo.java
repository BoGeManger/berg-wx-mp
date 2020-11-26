package com.berg.vo.mp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MpQrCodeTicketVo {

    @ApiModelProperty(value = "场景id")
    String sceneStr;
    @ApiModelProperty(value = "获取的二维码ticket，凭借此ticket可以在有效时间内换取二维码")
    String ticket;
    @ApiModelProperty(value = "该二维码有效时间，以秒为单位。 最大不超过2592000（即30天）")
    Integer expireSeconds;
    @ApiModelProperty(value = "二维码图片解析后的地址，开发者可根据该地址自行生成需要的二维码图片")
    String url;
}
