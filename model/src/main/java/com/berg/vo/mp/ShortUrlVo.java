package com.berg.vo.mp;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShortUrlVo {

    @ApiModelProperty(value = "微信公众号短链接表id")
    Integer id;
    @ApiModelProperty(value = "长链接")
    String longUrl;
    @ApiModelProperty(value = "短链接")
    String shortUrl;
    @ApiModelProperty(value = "描述")
    String remark;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    LocalDateTime createTime;
    @ApiModelProperty(value = "创建用户")
    String createUser;
}
