package com.berg.vo.mp.in;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MpMaterialDeleteInVo {

    @NotBlank(message = "微信公众号appId不能为空")
    @ApiModelProperty(value = "微信公众号appId")
    String appId;
    @NotBlank(message = "媒体id不能为空")
    @ApiModelProperty(value = "媒体id")
    String mediaId;
}
