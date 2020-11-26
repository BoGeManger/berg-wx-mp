package com.berg.vo.mp.in;

import com.berg.vo.common.PageInVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class GetMsgRecordPageInVo extends PageInVo {

    @NotBlank(message = "微信公众号appId不能为空")
    @ApiModelProperty(value = "微信公众号appId")
    String appId;
    @ApiModelProperty(value = "模板消息id")
    String templateId;
    @ApiModelProperty(value = "描述")
    String remark;
}
