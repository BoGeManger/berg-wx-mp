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
 * 会员公众号关键字自动回复表
 * </p>
 *
 * @author 
 * @since 2020-12-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mp_keys_reply_tbl")
public class KeysReplyTbl implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 会员公众号关键字自动回复表id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 微信公众号appId
     */
    private String appId;

    /**
     * 关键词内容
     */
    private String keyContent;

    /**
     * 回复内容
     */
    private String replyContent;

    /**
     * 回复内容html格式
     */
    private String replyContentHtml;

    /**
     * 素材媒体id
     */
    private String mediaId;

    /**
     * 素材文件类型(image 图片 voice 语音 video 视频 thumb 缩略图)
     */
    private String mediaType;

    /**
     * 图文类型点击跳转链接
     */
    private String url;

    /**
     * 图文类型封面图片链接
     */
    private String picUrl;

    /**
     * 图文类型描述
     */
    private String remark;

    /**
     * 回复内容类型(0 文本 1 图文 2 素材)
     */
    private Integer replyType;

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
