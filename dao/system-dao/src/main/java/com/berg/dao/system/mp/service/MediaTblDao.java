package com.berg.dao.system.mp.service;

import com.berg.dao.system.mp.entity.MediaTbl;
import com.berg.dao.base.IService;
import com.berg.dao.system.mp.mapper.MediaTblMapper;

/**
 * <p>
 * 微信公众号素材表 服务类
 * </p>
 *
 * @author 
 * @since 2020-12-02
 */
public interface MediaTblDao extends IService<MediaTbl> {
   MediaTblMapper getMapper();
}
