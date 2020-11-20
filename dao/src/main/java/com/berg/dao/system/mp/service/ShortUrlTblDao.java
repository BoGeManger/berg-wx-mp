package com.berg.dao.system.mp.service;

import com.berg.dao.system.mp.entity.ShortUrlTbl;
import com.berg.dao.base.IService;
import com.berg.dao.system.mp.mapper.ShortUrlTblMapper;

/**
 * <p>
 * 微信公众号短链接表 服务类
 * </p>
 *
 * @author 
 * @since 2020-11-20
 */
public interface ShortUrlTblDao extends IService<ShortUrlTbl> {
   ShortUrlTblMapper getMapper();
}
