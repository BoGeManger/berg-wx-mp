package com.berg.vo.mp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class MpMenuVo {

    @Valid
    @NotNull(message = "一级菜单不能为空")
    @Size(max = 5, message = "一级菜单不能超过5个")
    @ApiModelProperty(value = "一级菜单")
    List<MpMenuButtonVo> buttons;
    @ApiModelProperty(value = "菜单匹配规则")
    MpMenuRuleVo matchRule;
}
