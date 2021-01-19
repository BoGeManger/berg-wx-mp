package com.berg.vo.mp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MpTemplateVo {
    
    @ApiModelProperty(value = "模板ID")
    String templateId;
    @ApiModelProperty(value = "模板标题")
    String title;
    @ApiModelProperty(value = "模板所属行业的一级行业")
    String primaryIndustry;
    @ApiModelProperty(value = "模板所属行业的二级行业")
    String deputyIndustry;
    @ApiModelProperty(value = "模板内容")
    String content;
    @ApiModelProperty(value = "模板示例")
    String example;
}
