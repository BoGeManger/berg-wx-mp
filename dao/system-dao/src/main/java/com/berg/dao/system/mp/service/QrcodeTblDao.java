package com.berg.dao.system.mp.service;

import com.berg.dao.system.mp.entity.QrcodeTbl;
import com.berg.dao.base.IService;
import com.berg.dao.system.mp.mapper.QrcodeTblMapper;

/**
 * <p>
 * 微信公众号二维码表 服务类
 * </p>
 *
 * @author 
 * @since 2020-11-26
 */
public interface QrcodeTblDao extends IService<QrcodeTbl> {
   QrcodeTblMapper getMapper();
}
