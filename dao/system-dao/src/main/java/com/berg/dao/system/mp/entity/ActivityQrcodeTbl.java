package com.berg.dao.system.mp.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 微信公众号活动二维码表
 * </p>
 *
 * @author 
 * @since 2020-12-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mp_activity_qrcode_tbl")
public class ActivityQrcodeTbl implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 微信公众号活动二维码表id
     */
    private String id;

    /**
     * 微信公众号appId
     */
    private String appId;

    /**
     * 活动名称
     */
    private String name;

    /**
     * 活动编码
     */
    private String code;

    /**
     * 活动描述
     */
    private String remark;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建用户
     */
    private String createUser;

    /**
     * 更新时间
     */
    private LocalDateTime modifyTime;

    /**
     * 修改用户
     */
    private String modifyUser;

    /**
     * 删除时间
     */
    private LocalDateTime delTime;

    /**
     * 删除用户
     */
    private String delUser;

    /**
     * 是否删除(0 否,1 是)
     */
    private Integer isdel;


}
