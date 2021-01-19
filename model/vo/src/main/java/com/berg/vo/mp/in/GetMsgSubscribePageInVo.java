package com.berg.vo.mp.in;

import com.berg.vo.common.PageInVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class GetMsgSubscribePageInVo extends PageInVo {

    @NotBlank(message = "微信公众号appId不能为空")
    @ApiModelProperty(value = "微信公众号appId")
    String appId;
    @NotBlank(message = "消息发布编号不能为空")
    @ApiModelProperty(value = "消息发布编号")
    String publishId;
    @ApiModelProperty(value = "微信公众号openId")
    String openId;
    @ApiModelProperty(value = "描述")
    String remark;
    @ApiModelProperty(value = "状态(0 有效,1 无效)")
    Integer status;
}
