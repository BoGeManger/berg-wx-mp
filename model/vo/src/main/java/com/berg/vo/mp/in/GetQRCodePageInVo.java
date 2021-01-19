package com.berg.vo.mp.in;

import com.berg.vo.common.PageInVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class GetQRCodePageInVo extends PageInVo {

    @NotBlank(message = "微信公众号appId不能为空")
    @ApiModelProperty(value = "微信公众号appId")
    String appId;
    @ApiModelProperty(value = "场景值ID（字符串形式的ID），字符串类型，长度限制为1到64，可使用活动编码")
    String sceneStr;
    @ApiModelProperty(value = "描述")
    String remark;
}
