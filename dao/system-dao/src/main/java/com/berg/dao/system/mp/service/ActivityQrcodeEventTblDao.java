package com.berg.dao.system.mp.service;

import com.berg.dao.system.mp.entity.ActivityQrcodeEventTbl;
import com.berg.dao.base.IService;
import com.berg.dao.system.mp.mapper.ActivityQrcodeEventTblMapper;

/**
 * <p>
 * 微信公众号活动二维码事件表 服务类
 * </p>
 *
 * @author 
 * @since 2020-12-03
 */
public interface ActivityQrcodeEventTblDao extends IService<ActivityQrcodeEventTbl> {
   ActivityQrcodeEventTblMapper getMapper();
}
