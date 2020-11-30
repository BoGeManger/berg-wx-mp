package com.berg.vo.mp.in;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class TemplateSubscribeInVo {

    @NotBlank(message = "微信公众号消息发布表id不能为空")
    @ApiModelProperty(value = "微信公众号消息发布表id")
    String publishId;
    @Size(max = 255, message = "描述长度不能超过255个字符")
    @ApiModelProperty(value = "描述")
    String remark;
}
