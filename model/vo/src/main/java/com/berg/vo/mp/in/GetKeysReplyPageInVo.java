package com.berg.vo.mp.in;

import com.berg.vo.common.PageInVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class GetKeysReplyPageInVo extends PageInVo {

    @NotBlank(message = "微信公众号appId不能为空")
    @ApiModelProperty(value = "微信公众号appId")
    String appId;
    @ApiModelProperty(value = "关键词内容")
    String keyContent;
    @ApiModelProperty(value = "描述")
    String remark;
}
