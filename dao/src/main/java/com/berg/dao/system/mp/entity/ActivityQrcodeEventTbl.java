package com.berg.dao.system.mp.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 微信公众号活动二维码事件表
 * </p>
 *
 * @author 
 * @since 2020-12-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mp_activity_qrcode_event_tbl")
public class ActivityQrcodeEventTbl implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 微信公众号活动二维码事件表id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 微信公众号appId
     */
    private String appId;

    /**
     * 活动id
     */
    private String activityId;

    /**
     * 事件(scan 扫码 subscribe 关注)
     */
    private String event;

    /**
     * 绑定关键字
     */
    private String keys;


}
