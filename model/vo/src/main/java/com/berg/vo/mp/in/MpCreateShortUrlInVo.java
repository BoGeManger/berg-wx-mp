package com.berg.vo.mp.in;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class MpCreateShortUrlInVo {

    @NotBlank(message = "微信公众号appId不能为空")
    @ApiModelProperty(value = "微信公众号appId")
    String appId;
    @Size(max = 255, message = "长链接长度不能超过255个字符")
    @NotBlank(message = "长链接不能为空")
    @ApiModelProperty(value = "长链接")
    String longUrl;
    @Size(max = 255, message = "描述长度不能超过255个字符")
    @ApiModelProperty(value = "描述")
    String remark;
}
