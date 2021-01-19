package com.berg.vo.mp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MpNetCheckPingInfoVo {

    @ApiModelProperty(value = "ping的ip，执行命令为ping ip –c 1-w 1 -q")
    String ip;
    @ApiModelProperty(value = "ping的源头的运营商，由请求中的check_operator控制")
    String fromOperator;
    @ApiModelProperty(value = "ping的丢包率，0%表示无丢包，100%表示全部丢包。因为目前仅发送一个ping包，因此取值仅有0%或者100%两种可")
    String packageLoss;
    @ApiModelProperty(value = "ping的耗时，取ping结果的avg耗时")
    String time;
}
