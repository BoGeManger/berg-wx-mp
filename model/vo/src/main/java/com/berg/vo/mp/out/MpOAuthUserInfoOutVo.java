package com.berg.vo.mp.out;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MpOAuthUserInfoOutVo {

    @ApiModelProperty(value = "普通用户的标识，对当前开发者帐号唯一")
    String openid;
    @ApiModelProperty(value = "普通用户昵称")
    String nickname;
    @ApiModelProperty(value = "普通用户性别，1为男性，2为女性")
    Integer sex;
    @ApiModelProperty(value = "普通用户个人资料填写的城市")
    String city;
    @ApiModelProperty(value = "普通用户个人资料填写的省份")
    String province;
    @ApiModelProperty(value = "国家，如中国为CN")
    String country;
    @ApiModelProperty(value = "用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像）")
    String headImgUrl;
    @ApiModelProperty(value = "用户统一标识。针对一个微信开放平台帐号下的应用，同一用户的unionid是唯一的")
    String unionId;
    @ApiModelProperty(value = "用户特权信息，json数组，如微信沃卡用户为（chinaunicom）")
    String[] privileges;
}
