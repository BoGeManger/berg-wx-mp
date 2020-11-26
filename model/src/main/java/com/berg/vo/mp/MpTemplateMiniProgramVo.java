package com.berg.vo.mp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MpTemplateMiniProgramVo {

    @ApiModelProperty(value = "小程序appId")
    String appid;
    @ApiModelProperty(value = "小程序跳转页面")
    String pagePath;
    @ApiModelProperty(value = "是否跳转页面")
    Boolean usePath = false;
}
