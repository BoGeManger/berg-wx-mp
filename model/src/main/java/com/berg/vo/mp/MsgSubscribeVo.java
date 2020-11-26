package com.berg.vo.mp;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MsgSubscribeVo {

    @ApiModelProperty(value = "微信公众号消息订阅表id")
    Integer id;
    @ApiModelProperty(value = "微信开放平台unionId")
    String unionId;
    @ApiModelProperty(value = "微信公众号openId")
    String openId;
    @ApiModelProperty(value = "会员表id")
    String memberId;
    @ApiModelProperty(value = "执行次数")
    Integer executeNum;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "执行时间")
    LocalDateTime executeTime;
    @ApiModelProperty(value = "描述")
    String remark;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    LocalDateTime createTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间")
    LocalDateTime modifyTime;
    @ApiModelProperty(value = "状态(0 有效,1 无效)")
    Integer status;
}
