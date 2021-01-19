package com.berg.vo.mp;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MsgPublishVo {

    @ApiModelProperty(value = "微信公众号消息发布表id")
    String id;
    @ApiModelProperty(value = "周期(self单次,day每天,week每周,month每月,year每年)")
    String publishCycle;
    @ApiModelProperty(value = "推送具体时间")
    String publishTime;
    @ApiModelProperty(value = "推送时间段")
    String publishDate;
    @ApiModelProperty(value = "推送次数限制")
    Integer publishLimit;
    @ApiModelProperty(value = "推送人群（0 向自定义人群推送 1 向订阅人群推送）")
    Integer publishPeople;
    @ApiModelProperty(value = "操作类型(0 手动触发 1定时触发)")
    Integer operateType;
    @ApiModelProperty(value = "描述")
    String remark;
    @ApiModelProperty(value = "创建人")
    String createUser;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    LocalDateTime createTime;
    @ApiModelProperty(value = "更新人")
    String modifyUser;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间")
    LocalDateTime modifyTime;
    @ApiModelProperty(value = "状态(0 有效,1 无效)")
    Integer status;
}
