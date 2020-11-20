package com.berg.dao.system.mb.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 会员公众号绑定表
 * </p>
 *
 * @author 
 * @since 2020-11-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mb_mp_bind_tbl")
public class MpBindTbl implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 会员公众号绑定表id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 微信公众号appId
     */
    private String appId;

    /**
     * 微信开放平台unionId
     */
    private String unionId;

    /**
     * 微信公众号openId
     */
    private String openId;

    /**
     * 会员表id
     */
    private String memberId;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像链接
     */
    private String headImgUrl;

    /**
     * 性别(1为男性，2为女性)
     */
    private Integer gender;

    /**
     * 国家
     */
    private String country;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 用户特权信息，json数组，如微信沃卡用户为（chinaunicom）
     */
    private String privileges;

    /**
     * 是否已关注(0 否 1 是)
     */
    private Integer subscribe;

    /**
     * 关注时间
     */
    private LocalDateTime subscribeTime;

    /**
     * 返回用户关注的渠道来源(ADD_SCENE_SEARCH 公众号搜索，ADD_SCENE_ACCOUNT_MIGRATION 公众号迁移，ADD_SCENE_PROFILE_CARD 名片分享，ADD_SCENE_QR_CODE 扫描二维码，ADD_SCENEPROFILE LINK 图文页内名称点击，ADD_SCENE_PROFILE_ITEM 图文页右上角菜单，ADD_SCENE_PAID 支付后关注，ADD_SCENE_OTHERS 其他)
     */
    private String subscribeScene;

    /**
     * 标签信息,json数组
     */
    private String tagIds;

    /**
     * 二维码扫码场景（开发者自定义）
     */
    private String qrScene;

    /**
     * 二维码扫码场景描述（开发者自定义）
     */
    private String qrSceneStr;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime modifyTime;


}
