package com.berg.vo.mp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ActivityQRCodeEventVo {

    @NotNull(message = "微信公众号活动二维码事件表id不能为空")
    @Min(value = 0,message = "微信公众号活动二维码事件表id不能小于0")
    @ApiModelProperty(value = "微信公众号活动二维码事件表id")
    Integer id;
    @Size(max = 20, message = "事件长度不能超过20个字符")
    @NotBlank(message = "事件不能为空")
    @ApiModelProperty(value = "事件(scan 扫码 subscribe 关注)")
    String event;
    @Size(max = 255, message = "绑定关键字长度不能超过255个字符")
    @NotBlank(message = "绑定关键字不能为空")
    @ApiModelProperty(value = "绑定关键字")
    String keys;
}
