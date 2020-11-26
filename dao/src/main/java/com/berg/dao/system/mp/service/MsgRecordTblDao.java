package com.berg.dao.system.mp.service;

import com.berg.dao.system.mp.entity.MsgRecordTbl;
import com.berg.dao.base.IService;
import com.berg.dao.system.mp.mapper.MsgRecordTblMapper;

/**
 * <p>
 * 公众号模板消息记录表 服务类
 * </p>
 *
 * @author 
 * @since 2020-11-26
 */
public interface MsgRecordTblDao extends IService<MsgRecordTbl> {
   MsgRecordTblMapper getMapper();
}
