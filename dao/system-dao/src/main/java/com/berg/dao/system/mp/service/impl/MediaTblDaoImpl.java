package com.berg.dao.system.mp.service.impl;

import com.berg.dao.constant.DataSource;
import com.berg.dao.system.mp.entity.MediaTbl;
import com.berg.dao.system.mp.mapper.MediaTblMapper;
import com.berg.dao.system.mp.service.MediaTblDao;
import com.berg.dao.base.ServiceImpl;
import org.springframework.stereotype.Repository;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;

/**
 * <p>
 * 微信公众号素材表 服务实现类
 * </p>
 *
 * @author 
 * @since 2020-12-02
 */
@DS(DataSource.SYSTEM)
@Repository("system.MediaTblDaoImpl")
public class MediaTblDaoImpl extends ServiceImpl<MediaTblMapper, MediaTbl> implements MediaTblDao {

    @Override
    public MediaTblMapper getMapper(){
      DynamicDataSourceContextHolder.push(DataSource.SYSTEM);
      return this.getBaseMapper();
    }
}
