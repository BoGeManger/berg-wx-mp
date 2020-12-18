package com.berg.dao.system.mp.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 微信公众号消息发布表
 * </p>
 *
 * @author 
 * @since 2020-11-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mp_msg_publish_tbl")
public class MsgPublishTbl implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 微信公众号消息发布表id
     */
    private String id;

    /**
     * 微信公众号appId
     */
    private String appId;

    /**
     * 周期(self单次,day每天,week每周,month每月,year每年)
     */
    private String publishCycle;

    /**
     * 推送具体时间
     */
    private String publishTime;

    /**
     * 推送时间段
     */
    private String publishDate;

    /**
     * 推送次数限制
     */
    private Integer publishLimit;

    /**
     * 推送人群（0 向自定义人群推送 1 向订阅人群推送）
     */
    private Integer publishPeople;

    /**
     * 操作类型(0 手动触发 1定时触发)
     */
    private Integer operateType;

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
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    private String modifyUser;

    /**
     * 更新时间
     */
    private LocalDateTime modifyTime;

    /**
     * 状态(0 有效,1 无效)
     */
    private Integer status;


}
