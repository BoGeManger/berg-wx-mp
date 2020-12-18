package com.berg.dao.system.mp.service.impl;

import com.berg.dao.system.mp.entity.ActivityQrcodeTbl;
import com.berg.dao.system.mp.mapper.ActivityQrcodeTblMapper;
import com.berg.dao.system.mp.service.ActivityQrcodeTblDao;
import com.berg.dao.base.ServiceImpl;
import org.springframework.stereotype.Repository;
import com.berg.dao.constant.DataSource;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;

/**
 * <p>
 * 微信公众号活动二维码表 服务实现类
 * </p>
 *
 * @author 
 * @since 2020-12-03
 */
@DS(DataSource.SYSTEM)
@Repository("system.ActivityQrcodeTblDaoImpl")
public class ActivityQrcodeTblDaoImpl extends ServiceImpl<ActivityQrcodeTblMapper, ActivityQrcodeTbl> implements ActivityQrcodeTblDao {

    @Override
    public ActivityQrcodeTblMapper getMapper(){
      DynamicDataSourceContextHolder.push(DataSource.SYSTEM);
      return this.getBaseMapper();
    }
}
