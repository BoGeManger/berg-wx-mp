package com.berg.dao.system.mp.service.impl;

import com.berg.dao.system.mp.entity.ActivityQrcodeRecordTbl;
import com.berg.dao.system.mp.mapper.ActivityQrcodeRecordTblMapper;
import com.berg.dao.system.mp.service.ActivityQrcodeRecordTblDao;
import com.berg.dao.base.ServiceImpl;
import org.springframework.stereotype.Repository;
import com.berg.dao.constant.DataSource;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2020-12-03
 */
@DS(DataSource.SYSTEM)
@Repository("system.ActivityQrcodeRecordTblDaoImpl")
public class ActivityQrcodeRecordTblDaoImpl extends ServiceImpl<ActivityQrcodeRecordTblMapper, ActivityQrcodeRecordTbl> implements ActivityQrcodeRecordTblDao {

    @Override
    public ActivityQrcodeRecordTblMapper getMapper(){
      DynamicDataSourceContextHolder.push(DataSource.SYSTEM);
      return this.getBaseMapper();
    }
}
