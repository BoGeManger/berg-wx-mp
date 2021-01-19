package com.berg.vo.mp.in;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MpGetInterfaceSummaryInVo {

    @NotBlank(message = "微信公众号appId不能为空")
    @ApiModelProperty(value = "微信公众号appId")
    String appId;
    @ApiModelProperty(value = "获取数据的起始日期，begin_date和end_date的差值需小于“最大时间跨度”（比如最大时间跨度为1时，begin_date和end_date的差值只能为0，才能小于1），否则会报错")
    String beginDate;
    @ApiModelProperty(value = "获取数据的结束日期，end_date允许设置的最大值为昨日")
    String endDate;
}
