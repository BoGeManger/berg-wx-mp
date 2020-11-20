package com.berg.vo.mp.in;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MpNetCheckInVo {

    @NotBlank(message = "微信公众号appId不能为空")
    @ApiModelProperty(value = "微信公众号appId")
    String appId;
    @NotBlank(message = "执行的检测动作不能为空")
    @ApiModelProperty(value = "执行的检测动作，允许的值：dns（做域名解析）、ping（做ping检测）、all（dns和ping都做）")
    String action="all";
    @NotBlank(message = "指定平台不能为空")
    @ApiModelProperty(value = "指定平台从某个运营商进行检测，允许的值：CHINANET（电信出口）、UNICOM（联通出口）、CAP（腾讯自建出口）、DEFAULT（根据ip来选择运营商）")
    String checkOperator="DEFAULT";
}
