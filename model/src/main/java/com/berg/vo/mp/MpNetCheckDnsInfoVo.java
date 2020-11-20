package com.berg.vo.mp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MpNetCheckDnsInfoVo {

    @ApiModelProperty(value = "解析出来的ip")
    String ip;
    @ApiModelProperty(value = "ip对应的运营商")
    String realOperator;
}
