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
 * 公众号模板消息记录表
 * </p>
 *
 * @author 
 * @since 2020-11-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mp_msg_record_tbl")
public class MsgRecordTbl implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 公众号模板消息记录表id
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
     * 消息id
     */
    private String msgId;

    /**
     * 模板消息id
     */
    private String templateId;

    /**
     * 模板消息内容
     */
    private String data;

    /**
     * 网站跳转地址
     */
    private String url;

    /**
     * 关联小程序appid
     */
    private String miniappAppid;

    /**
     * 关联小程序跳转地址
     */
    private String miniappPage;

    /**
     * 是否使用小程序跳转(0 否 1 是)
     */
    private Integer userMiniappPath;

    /**
     * 描述
     */
    private String remark;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
