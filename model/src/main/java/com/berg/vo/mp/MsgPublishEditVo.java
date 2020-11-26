package com.berg.vo.mp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class MsgPublishEditVo {

    @NotBlank(message = "微信公众号消息发布表id不能为空")
    @ApiModelProperty(value = "微信公众号消息发布表id")
    String id;
    @NotBlank(message = "微信公众号appId不能为空")
    @ApiModelProperty(value = "微信公众号appId")
    String appId;
    @NotBlank(message = "周期不能为空")
    @ApiModelProperty(value = "周期(self单次,day每天,week每周,month每月,year每年)")
    String publishCycle;
    @ApiModelProperty(value = "推送具体时间(当选择推送周期是每天，每周，每月时格式为HH:mm:ss;当选择周期为每年时格式为MM-dd HH:mm:ss;单次时为yyyy-MM-dd HH:mm:ss)")
    String publishTime;
    @ApiModelProperty(value = "推送时间段(当选择推送周期是每天，每年，单次时不需要填写，当选择周期是每周时，这个字段填写1-7对应星期一到星期天；当选择周期是每月时，这个字段填写1-31表示号数)")
    String publishDate;
    @NotNull(message = "推送次数限制不能为空")
    @ApiModelProperty(value = "推送次数限制")
    Integer publishLimit;
    @NotNull(message = "推送人群不能为空")
    @ApiModelProperty(value = "推送人群（0 向自定义人群推送 1 向订阅人群推送）")
    Integer publishPeople;
    @NotNull(message = "操作类型不能为空")
    @ApiModelProperty(value = "操作类型(0 手动触发 1定时触发)")
    Integer operateType;
    @NotBlank(message = "模板消息id不能为空")
    @ApiModelProperty(value = "模板消息id")
    String templateId;
    @NotBlank(message = "模板消息内容不能为空")
    @ApiModelProperty(value = "模板消息内容")
    String data;
    @ApiModelProperty(value = "网站跳转地址")
    String url;
    @ApiModelProperty(value = "关联小程序appid")
    String miniappAppid;
    @ApiModelProperty(value = "关联小程序跳转地址")
    String miniappPage;
    @ApiModelProperty(value = "是否使用小程序跳转(0 否 1 是)")
    Integer userMiniappPath;
    @ApiModelProperty(value = "描述")
    String remark;
    @NotNull(message = "状态不能为空")
    @ApiModelProperty(value = "状态(0 有效,1 无效)")
    Integer status;
    @ApiModelProperty(value = "指定人群")
    List<String> openIds;
}
