package com.berg.dao.system.mp.service.impl;

import com.berg.dao.system.mp.entity.ActivityQrcodeEventTbl;
import com.berg.dao.system.mp.mapper.ActivityQrcodeEventTblMapper;
import com.berg.dao.system.mp.service.ActivityQrcodeEventTblDao;
import com.berg.dao.base.ServiceImpl;
import org.springframework.stereotype.Repository;
import com.berg.dao.constant.DataSource;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;

/**
 * <p>
 * 微信公众号活动二维码事件表 服务实现类
 * </p>
 *
 * @author 
 * @since 2020-12-03
 */
@DS(DataSource.SYSTEM)
@Repository("system.ActivityQrcodeEventTblDaoImpl")
public class ActivityQrcodeEventTblDaoImpl extends ServiceImpl<ActivityQrcodeEventTblMapper, ActivityQrcodeEventTbl> implements ActivityQrcodeEventTblDao {

    @Override
    public ActivityQrcodeEventTblMapper getMapper(){
      DynamicDataSourceContextHolder.push(DataSource.SYSTEM);
      return this.getBaseMapper();
    }
}
