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
 * 
 * </p>
 *
 * @author 
 * @since 2020-12-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mp_activity_qrcode_record_tbl")
public class ActivityQrcodeRecordTbl implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 微信公众号活动二维码表id
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
     * 活动编码
     */
    private String code;

    /**
     * 事件(scan 扫码 subscribe 关注)
     */
    private String event;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
