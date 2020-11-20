package com.berg.vo.mp.in;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MpLoginInVo {

    @NotBlank(message = "微信公众号code不能为空")
    @ApiModelProperty(value = "微信公众号code")
    String code;
}
