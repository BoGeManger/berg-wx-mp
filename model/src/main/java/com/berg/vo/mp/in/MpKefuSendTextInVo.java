package com.berg.vo.mp.in;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MpKefuSendTextInVo {

    @ApiModelProperty(value = "普通用户openid")
    String toUser;
    @ApiModelProperty(value = "文本消息内容")
    String content;

}
