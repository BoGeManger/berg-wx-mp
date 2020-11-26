package com.berg.dao.system.mp.service;

import com.berg.dao.system.mp.entity.MsgSubscribeTbl;
import com.berg.dao.base.IService;
import com.berg.dao.system.mp.mapper.MsgSubscribeTblMapper;

/**
 * <p>
 * 微信公众号消息订阅表 服务类
 * </p>
 *
 * @author 
 * @since 2020-11-26
 */
public interface MsgSubscribeTblDao extends IService<MsgSubscribeTbl> {
   MsgSubscribeTblMapper getMapper();
}
