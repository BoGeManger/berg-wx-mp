package com.berg.dao.system.mp.service.impl;

import com.berg.dao.constant.DataSource;
import com.berg.dao.system.mp.entity.ShortUrlTbl;
import com.berg.dao.system.mp.mapper.ShortUrlTblMapper;
import com.berg.dao.system.mp.service.ShortUrlTblDao;
import com.berg.dao.base.ServiceImpl;
import org.springframework.stereotype.Repository;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;

/**
 * <p>
 * 微信公众号短链接表 服务实现类
 * </p>
 *
 * @author 
 * @since 2020-11-20
 */
@DS(DataSource.SYSTEM)
@Repository("system.ShortUrlTblDaoImpl")
public class ShortUrlTblDaoImpl extends ServiceImpl<ShortUrlTblMapper, ShortUrlTbl> implements ShortUrlTblDao {

    @Override
    public ShortUrlTblMapper getMapper(){
      DynamicDataSourceContextHolder.push(DataSource.SYSTEM);
      return this.getBaseMapper();
    }
}
