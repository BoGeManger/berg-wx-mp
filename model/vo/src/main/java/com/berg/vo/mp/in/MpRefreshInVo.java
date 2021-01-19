package com.berg.vo.mp.in;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MpRefreshInVo {

    @ApiModelProperty(value = "微信公众号code(oauthAccessToken过期或需要获取微信最新信息时必填),不填则获取系统最新信息")
    String code;
}
