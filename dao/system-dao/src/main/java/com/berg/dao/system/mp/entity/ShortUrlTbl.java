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
 * 微信公众号短链接表
 * </p>
 *
 * @author 
 * @since 2020-11-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mp_short_url_tbl")
public class ShortUrlTbl implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 微信公众号短链接表id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 微信公众号appId
     */
    private String appId;

    /**
     * 长链接
     */
    private String longUrl;

    /**
     * 短链接
     */
    private String shortUrl;

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


}
