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
 * 微信公众号菜单表
 * </p>
 *
 * @author 
 * @since 2020-11-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mp_menu_tbl")
public class MenuTbl implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 微信公众号菜单表id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 微信公众号appId
     */
    private String appId;

    /**
     * 菜单记录
     */
    private String menu;

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
