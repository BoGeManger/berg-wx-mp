package com.berg.vo.member;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MpBindVo {

    @ApiModelProperty(value = "会员公众号绑定表id")
    Integer id;
    @ApiModelProperty(value = "微信公众号appId")
    String appId;
    @ApiModelProperty(value = "微信开放平台unionId")
    String unionId;
    @ApiModelProperty(value = "微信公众号openId")
    String openId;
    @ApiModelProperty(value = "会员表id")
    String memberId;
    @ApiModelProperty(value = "昵称")
    String nickname;
    @ApiModelProperty(value = "头像链接")
    String headImgUrl;
    @ApiModelProperty(value = "性别(1为男性，2为女性)")
    Integer gender;
    @ApiModelProperty(value = "国家")
    String country;
    @ApiModelProperty(value = "省")
    String province;
    @ApiModelProperty(value = "市")
    String city;
    @ApiModelProperty(value = "用户特权信息，json数组，如微信沃卡用户为（chinaunicom）")
    String privileges;
    @ApiModelProperty(value = "是否已关注(0 否 1 是)")
    Integer subscribe;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "关注时间")
    LocalDateTime subscribeTime;
    @ApiModelProperty(value = "返回用户关注的渠道来源(ADD_SCENE_SEARCH 公众号搜索，ADD_SCENE_ACCOUNT_MIGRATION 公众号迁移，ADD_SCENE_PROFILE_CARD 名片分享，ADD_SCENE_QR_CODE 扫描二维码，ADD_SCENEPROFILE LINK 图文页内名称点击，ADD_SCENE_PROFILE_ITEM 图文页右上角菜单，ADD_SCENE_PAID 支付后关注，ADD_SCENE_OTHERS 其他)")
    String subscribeScene;
    @ApiModelProperty(value = "标签信息,json数组")
    String tagIds;
    @ApiModelProperty(value = "二维码扫码场景（开发者自定义）")
    String qrScene;
    @ApiModelProperty(value = "二维码扫码场景描述（开发者自定义）")
    String qrSceneStr;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    LocalDateTime createTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间")
    LocalDateTime modifyTime;
}
