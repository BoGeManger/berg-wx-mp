package com.berg.vo.mp.out;

import com.berg.vo.mp.MpConditionalMenuVo;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class MpGetMenuOutVo {

    @ApiModelProperty(value = "菜单")
    MpConditionalMenuVo menu;
    @JsonProperty("conditionalmenu")
    @ApiModelProperty(value = "个性菜单")
    List<MpConditionalMenuVo> conditionalMenu;
}
