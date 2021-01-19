package com.berg.vo.mp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MpTemplateDataVo {

    @NotBlank(message = "键不能为空")
    @ApiModelProperty(value = "键")
    String name;
    @ApiModelProperty(value = "值")
    String value;
    @ApiModelProperty(value = "颜色")
    String color;
}
