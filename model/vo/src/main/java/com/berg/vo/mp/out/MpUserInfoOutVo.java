package com.berg.vo.mp.out;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MpUserInfoOutVo {

    @ApiModelProperty(value = "是否关注")
    Boolean subscribe;
    @ApiModelProperty(value = "微信公众号唯一标识")
    String openId;
    @ApiModelProperty(value = "昵称")
    String nickname;
    @ApiModelProperty(value = "性别描述信息：男、女、未知等")
    String sexDesc;
    @ApiModelProperty(value = "性别表示：1，2等数字.")
    Integer sex;
    @ApiModelProperty(value = "语言")
    String language;
    @ApiModelProperty(value = "市")
    String city;
    @ApiModelProperty(value = "省")
    String province;
    @ApiModelProperty(value = "国")
    String country;
    @ApiModelProperty(value = "头像地址")
    String headImgUrl;
    @ApiModelProperty(value = "关注时间")
    Long subscribeTime;
    @ApiModelProperty(value = "微信开放平台唯一标识,只有在将公众号绑定到微信开放平台帐号后，才会出现该字段,另外，在用户未关注公众号时，将不返回用户unionID信息")
    String unionId;
    @ApiModelProperty(value = "备注")
    String remark;
    @ApiModelProperty(value = "分组")
    Integer groupId;
    @ApiModelProperty(value = "标签")
    Long[] tagIds;
    @ApiModelProperty(value = "用户特权信息，json 数组，如微信沃卡用户为（chinaunicom")
    String[] privileges;
    @ApiModelProperty(value = "返回用户关注的渠道来源(ADD_SCENE_SEARCH 公众号搜索，ADD_SCENE_ACCOUNT_MIGRATION 公众号迁移，ADD_SCENE_PROFILE_CARD 名片分享，ADD_SCENE_QR_CODE 扫描二维码，ADD_SCENEPROFILE LINK 图文页内名称点击，ADD_SCENE_PROFILE_ITEM 图文页右上角菜单，ADD_SCENE_PAID 支付后关注，ADD_SCENE_OTHERS 其他)")
    String subscribeScene;
    @ApiModelProperty(value = "qr_scene 二维码扫码场景（开发者自定义）")
    String qrScene;
    @ApiModelProperty(value = "qr_scene_str 二维码扫码场景描述（开发者自定义）")
    String qrSceneStr;
}
