package com.berg.dao.system.mp.service;

import com.berg.dao.system.mp.entity.MsgPublishTbl;
import com.berg.dao.base.IService;
import com.berg.dao.system.mp.mapper.MsgPublishTblMapper;

/**
 * <p>
 * 微信公众号消息发布表 服务类
 * </p>
 *
 * @author 
 * @since 2020-11-26
 */
public interface MsgPublishTblDao extends IService<MsgPublishTbl> {
   MsgPublishTblMapper getMapper();
}
