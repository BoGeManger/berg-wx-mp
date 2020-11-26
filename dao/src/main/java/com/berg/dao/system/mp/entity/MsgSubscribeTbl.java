package com.berg.dao.system.mp.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 微信公众号消息订阅表
 * </p>
 *
 * @author 
 * @since 2020-11-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mp_msg_subscribe_tbl")
public class MsgSubscribeTbl implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 微信公众号消息订阅表id
     */
    private Integer id;

    /**
     * 微信公众号appId
     */
    private String appId;

    /**
     * 微信公众号消息发布表id
     */
    private String publishId;

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
     * 执行次数
     */
    private Integer executeNum;

    /**
     * 执行时间
     */
    private LocalDateTime executeTime;

    /**
     * 描述
     */
    private String remark;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime modifyTime;

    /**
     * 状态(0 有效,1 无效)
     */
    private Integer status;


}
