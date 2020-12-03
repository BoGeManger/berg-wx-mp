package com.berg.dao.system.mp.service;

import com.berg.dao.system.mp.entity.ActivityQrcodeTbl;
import com.berg.dao.base.IService;
import com.berg.dao.system.mp.mapper.ActivityQrcodeTblMapper;

/**
 * <p>
 * 微信公众号活动二维码表 服务类
 * </p>
 *
 * @author 
 * @since 2020-12-03
 */
public interface ActivityQrcodeTblDao extends IService<ActivityQrcodeTbl> {
   ActivityQrcodeTblMapper getMapper();
}
