package com.berg.dao.system.mb.service;

import com.berg.dao.system.mb.entity.MpBindTbl;
import com.berg.dao.base.IService;
import com.berg.dao.system.mb.mapper.MpBindTblMapper;

/**
 * <p>
 * 会员公众号绑定表 服务类
 * </p>
 *
 * @author 
 * @since 2020-11-19
 */
public interface MpBindTblDao extends IService<MpBindTbl> {
   MpBindTblMapper getMapper();
}
