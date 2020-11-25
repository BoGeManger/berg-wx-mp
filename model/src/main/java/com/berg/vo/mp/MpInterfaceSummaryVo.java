package com.berg.vo.mp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MpInterfaceSummaryVo {

    @ApiModelProperty(value = "数据的小时，包括从000到2300，分别代表的是[000,100)到[2300,2400)，即每日的第1小时和最后1小时")
    Integer refHour;
    @ApiModelProperty(value = "通过服务器配置地址获得消息后，被动回复用户消息的次数")
    Integer callbackCount;
    @ApiModelProperty(value = "上述动作的失败次数")
    Integer failCount;
    @ApiModelProperty(value = "总耗时，除以callback_count即为平均耗时")
    Integer totalTimeCost;
    @ApiModelProperty(value = "最大耗时")
    Integer maxTimeCost;
}
