package com.berg.dao.system.mp.service.impl;

import com.berg.dao.constant.DataSource;
import com.berg.dao.system.mp.entity.MsgRecordTbl;
import com.berg.dao.system.mp.mapper.MsgRecordTblMapper;
import com.berg.dao.system.mp.service.MsgRecordTblDao;
import com.berg.dao.base.ServiceImpl;
import org.springframework.stereotype.Repository;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;

/**
 * <p>
 * 公众号模板消息记录表 服务实现类
 * </p>
 *
 * @author 
 * @since 2020-11-26
 */
@DS(DataSource.SYSTEM)
@Repository("system.MsgRecordTblDaoImpl")
public class MsgRecordTblDaoImpl extends ServiceImpl<MsgRecordTblMapper, MsgRecordTbl> implements MsgRecordTblDao {

    @Override
    public MsgRecordTblMapper getMapper(){
      DynamicDataSourceContextHolder.push(DataSource.SYSTEM);
      return this.getBaseMapper();
    }
}
