package com.berg.vo.mp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ActivityQRCodeRecordStatisticsVo {

    @ApiModelProperty(value = "活动名称")
    String id;
    @ApiModelProperty(value = "活动名称")
    String name;
    @ApiModelProperty(value = "扫码总次数")
    Integer total;
    @ApiModelProperty(value = "扫码总人数")
    Integer scanNum;
    @ApiModelProperty(value = "扫码已关注人数")
    Integer subscribeNum;
    @ApiModelProperty(value = "扫码未关注人数")
    Integer unsubscribeNum;
}
