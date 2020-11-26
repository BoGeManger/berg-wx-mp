package com.berg.vo.mp.in;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Data
public class MpQRCodeCreateTmpInVo {

    @NotBlank(message = "场景值ID不能为空")
    @ApiModelProperty(value = "场景值ID（字符串形式的ID），字符串类型，长度限制为1到64，可使用活动编码")
    String sceneStr;
    @Max(value = 2592000,message = "二维码有效不能大于2592000")
    @ApiModelProperty(value = "该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒")
    Integer expireSeconds;
    @ApiModelProperty(value = "描述")
    String remark;
}
