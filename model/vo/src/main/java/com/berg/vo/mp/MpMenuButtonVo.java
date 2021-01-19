package com.berg.vo.mp;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
public class MpMenuButtonVo {

    @NotBlank(message = "菜单的响应动作类型不能为空")
    @ApiModelProperty(value = "菜单的响应动作类型(view表示网页类型 click表示点击类型 miniprogram表示小程序类型)")
    String type;
    @Size(max = 16, message = "菜单标题长度不能超过16个字符")
    @NotBlank(message = "菜单标题不能为空")
    @ApiModelProperty(value = "菜单标题")
    String name;
    @ApiModelProperty(value = "菜单KEY值，用于消息接口推送，click等点击类型必须")
    String key;
    @ApiModelProperty(value = "网页链接，用户点击菜单可打开链接，不超过1024字节。type为miniprogram时，不支持小程序的老版本客户端将打开本url,view、miniprogram类型必须")
    String url;
    @JsonProperty("media_id")
    @ApiModelProperty(value = "调用新增永久素材接口返回的合法media_id,media_id类型和view_limited类型必须")
    String mediaId;
    @JsonProperty("appid")
    @ApiModelProperty(value = "小程序的appid,miniprogram类型必须")
    String appId;
    @JsonProperty("pagepath")
    @ApiModelProperty(value = "小程序的页面路径,miniprogram类型必须")
    String pagePath;
    @Valid
    @Size(max = 5, message = "二级菜单不能超过5个")
    @ApiModelProperty(value = "二级菜单")
    List<MpMenuButtonVo> subButtons = new ArrayList<>();
}
