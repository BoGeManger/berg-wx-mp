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
 * 微信公众号素材表
 * </p>
 *
 * @author 
 * @since 2020-12-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mp_media_tbl")
public class MediaTbl implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 微信公众号素材表id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 微信公众号appId
     */
    private String appId;

    /**
     * 素材媒体id
     */
    private String mediaId;

    /**
     * 素材链接
     */
    private String url;

    /**
     * 素材文件上传时间戳
     */
    private Integer createdAt;

    /**
     * 素材文件类型(image 图片 voice 语音 video 视频 thumb 缩略图)
     */
    private String mediaType;

    /**
     * 素材类型(0 临时素材 1 永久素材)
     */
    private Integer type;

    /**
     * 描述
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
