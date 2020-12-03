package com.berg.vo.mp.in;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class MpBatchTagInVo {

    @NotBlank(message = "微信公众号appId不能为空")
    @ApiModelProperty(value = "微信公众号appId")
    String appId;
    @NotBlank(message = "微信公众号标签id不能为空")
    @ApiModelProperty(value = "微信公众号标签id")
    Long tagId;
    @NotNull(message = "微信公众号openId不能为空")
    @Size(min = 1, message = "微信公众号openId不能为空")
    @ApiModelProperty(value = "微信公众号openId")
    List<String> openId;
}
