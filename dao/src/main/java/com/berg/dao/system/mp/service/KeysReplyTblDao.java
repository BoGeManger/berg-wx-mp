package com.berg.dao.system.mp.service;

import com.berg.dao.system.mp.entity.KeysReplyTbl;
import com.berg.dao.base.IService;
import com.berg.dao.system.mp.mapper.KeysReplyTblMapper;

/**
 * <p>
 * 会员公众号关键字自动回复表 服务类
 * </p>
 *
 * @author 
 * @since 2020-12-02
 */
public interface KeysReplyTblDao extends IService<KeysReplyTbl> {
   KeysReplyTblMapper getMapper();
}
