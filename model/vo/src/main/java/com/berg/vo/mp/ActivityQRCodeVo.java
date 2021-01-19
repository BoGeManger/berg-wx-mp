package com.berg.vo.mp;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ActivityQRCodeVo {

    @ApiModelProperty(value = "微信公众号活动二维码表id")
    String id;
    @ApiModelProperty(value = "活动名称")
    String name;
    @ApiModelProperty(value = "活动编码")
    String code;
    @ApiModelProperty(value = "活动描述")
    String remark;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    LocalDateTime createTime;
    @ApiModelProperty(value = "创建用户")
    String createUser;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间")
    LocalDateTime modifyTime;
    @ApiModelProperty(value = "修改用户")
    String modifyUser;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "删除时间")
    LocalDateTime delTime;
    @ApiModelProperty(value = "删除用户")
    String delUser;
    @ApiModelProperty(value = "是否删除(0 否,1 是)")
    Integer isdel;
}
