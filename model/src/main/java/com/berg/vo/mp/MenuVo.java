package com.berg.vo.mp;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MenuVo {

    @ApiModelProperty(value = "微信公众号菜单表id")
    Integer id;
    @ApiModelProperty(value = "菜单记录")
    String menu;
    @ApiModelProperty(value = "描述")
    String remark;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    LocalDateTime createTime;
    @ApiModelProperty(value = "创建用户")
    String createUser;
}
