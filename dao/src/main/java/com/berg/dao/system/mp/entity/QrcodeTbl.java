package com.berg.dao.system.mp.entity;

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
 * 微信公众号二维码表
 * </p>
 *
 * @author 
 * @since 2020-11-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mp_qrcode_tbl")
public class QrcodeTbl implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 微信公众号二维码表id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 微信公众号appId
     */
    private String appId;

    /**
     * 微信公众号openId
     */
    private String openId;

    /**
     * 场景id
     */
    private String sceneStr;

    /**
     * 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天）
     */
    private Integer expireSeconds;

    /**
     * 二维码ticket，凭借此ticket可以在有效时间内换取二维码
     */
    private String ticket;

    /**
     * 二维码图片解析后的地址
     */
    private String url;

    /**
     * 描述
     */
    private String remark;

    /**
     * 类型(0 临时二维码 1 永久二维码)
     */
    private Integer type;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
