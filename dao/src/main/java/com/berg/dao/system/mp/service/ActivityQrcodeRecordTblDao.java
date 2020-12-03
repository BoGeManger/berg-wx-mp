package com.berg.dao.system.mp.service;

import com.berg.dao.system.mp.entity.ActivityQrcodeRecordTbl;
import com.berg.dao.base.IService;
import com.berg.dao.system.mp.mapper.ActivityQrcodeRecordTblMapper;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2020-12-03
 */
public interface ActivityQrcodeRecordTblDao extends IService<ActivityQrcodeRecordTbl> {
   ActivityQrcodeRecordTblMapper getMapper();
}
