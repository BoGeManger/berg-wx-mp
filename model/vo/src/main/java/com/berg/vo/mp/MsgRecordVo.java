package com.berg.vo.mp;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MsgRecordVo {

    @ApiModelProperty(value = "公众号模板消息记录表id")
    Integer id;
    @ApiModelProperty(value = "微信公众号appId")
    String appId;
    @ApiModelProperty(value = "微信公众号openId")
    String openId;
    @ApiModelProperty(value = "消息id")
    String msgId;
    @ApiModelProperty(value = "模板消息id")
    String templateId;
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
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    LocalDateTime createTime;
}
