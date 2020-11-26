package com.berg.vo.mp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MpAppVo {

    @ApiModelProperty(value = "微信小程序appId")
    String appId;
    @ApiModelProperty(value = "微信小程序名称")
    String name;
}
