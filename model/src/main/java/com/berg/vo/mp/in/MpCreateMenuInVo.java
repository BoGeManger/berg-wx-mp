package com.berg.vo.mp.in;

import com.berg.vo.mp.MpMenuVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class MpCreateMenuInVo {

    @NotBlank(message = "微信公众号appId不能为空")
    @ApiModelProperty(value = "微信公众号appId")
    String appId;
    @Valid
    @NotNull(message = "微信公众号菜单不能为空")
    @ApiModelProperty(value = "微信公众号菜单")
    MpMenuVo menu;
    @ApiModelProperty(value = "操作备注")
    String remark;
}
