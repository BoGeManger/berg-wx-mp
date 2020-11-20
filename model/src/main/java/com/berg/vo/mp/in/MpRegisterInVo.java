package com.berg.vo.mp.in;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MpRegisterInVo {

    @NotBlank(message = "手机号码不能为空")
    @ApiModelProperty(value = "手机号码")
    String phone;
    @NotBlank(message = "验证码不能为空")
    @ApiModelProperty(value = "验证码")
    String code;
}
