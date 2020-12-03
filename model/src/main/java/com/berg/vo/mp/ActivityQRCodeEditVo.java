package com.berg.vo.mp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class ActivityQRCodeEditVo {

    @Size(max = 120, message = "微信公众号appId长度不能超过120个字符")
    @NotBlank(message = "微信公众号appId不能为空")
    @ApiModelProperty(value = "微信公众号活动二维码表id")
    String id;
    @Size(max = 120, message = "微信公众号appId长度不能超过120个字符")
    @NotBlank(message = "微信公众号appId不能为空")
    @ApiModelProperty(value = "微信公众号appId")
    String appId;
    @Size(max = 20, message = "活动名称长度不能超过20个字符")
    @NotBlank(message = "活动名称不能为空")
    @ApiModelProperty(value = "活动名称")
    String name;
    @Size(max = 20, message = "活动编码d长度不能超过20个字符")
    @NotBlank(message = "活动编码不能为空")
    @ApiModelProperty(value = "活动编码")
    String code;
    @Size(max = 255, message = "活动描述长度不能超过255个字符")
    @ApiModelProperty(value = "活动描述")
    String remark;
    @Valid
    @Size(max = 2,message = "事件不能超过2")
    @ApiModelProperty(value = "事件")
    List<ActivityQRCodeEventVo> events;
}
