package com.berg.vo.mp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MpUserTagVo {

    @ApiModelProperty(value = "标签id")
    Long id;
    @ApiModelProperty(value = "标签名称")
    String name;
    @ApiModelProperty(value = "已打标用户数")
    Integer count;
}
