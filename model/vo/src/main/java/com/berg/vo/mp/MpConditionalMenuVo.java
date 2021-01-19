package com.berg.vo.mp;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class MpConditionalMenuVo {

    @JsonProperty("button")
    @ApiModelProperty(value = "一级菜单")
    List<MpMenuButtonVo> buttons;
    @JsonProperty("matchrule")
    @ApiModelProperty(value = "菜单匹配规则")
    MpMenuRuleVo rule;
    @JsonProperty("menuid")
    @ApiModelProperty(value = "菜单id")
    String menuId;
}
