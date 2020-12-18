package com.berg.dao.system.mp.service.impl;

import com.berg.dao.constant.DataSource;
import com.berg.dao.system.mp.entity.KeysReplyTbl;
import com.berg.dao.system.mp.mapper.KeysReplyTblMapper;
import com.berg.dao.system.mp.service.KeysReplyTblDao;
import com.berg.dao.base.ServiceImpl;
import org.springframework.stereotype.Repository;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;

/**
 * <p>
 * 会员公众号关键字自动回复表 服务实现类
 * </p>
 *
 * @author 
 * @since 2020-12-02
 */
@DS(DataSource.SYSTEM)
@Repository("system.KeysReplyTblDaoImpl")
public class KeysReplyTblDaoImpl extends ServiceImpl<KeysReplyTblMapper, KeysReplyTbl> implements KeysReplyTblDao {

    @Override
    public KeysReplyTblMapper getMapper(){
      DynamicDataSourceContextHolder.push(DataSource.SYSTEM);
      return this.getBaseMapper();
    }
}
