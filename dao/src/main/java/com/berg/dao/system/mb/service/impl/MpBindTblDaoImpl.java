package com.berg.dao.system.mb.service.impl;

import com.berg.dao.system.mb.entity.MpBindTbl;
import com.berg.dao.system.mb.mapper.MpBindTblMapper;
import com.berg.dao.system.mb.service.MpBindTblDao;
import com.berg.dao.base.ServiceImpl;
import org.springframework.stereotype.Repository;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;

/**
 * <p>
 * 会员公众号绑定表 服务实现类
 * </p>
 *
 * @author 
 * @since 2020-11-19
 */
@DS("system")
@Repository("system.MpBindTblDaoImpl")
public class MpBindTblDaoImpl extends ServiceImpl<MpBindTblMapper, MpBindTbl> implements MpBindTblDao {

    @Override
    public MpBindTblMapper getMapper(){
      DynamicDataSourceContextHolder.push("system");
      return this.getBaseMapper();
    }
}
