package com.berg.dao.system.mp.service.impl;

import com.berg.dao.system.mp.entity.MsgSubscribeTbl;
import com.berg.dao.system.mp.mapper.MsgSubscribeTblMapper;
import com.berg.dao.system.mp.service.MsgSubscribeTblDao;
import com.berg.dao.base.ServiceImpl;
import org.springframework.stereotype.Repository;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;

/**
 * <p>
 * 微信公众号消息订阅表 服务实现类
 * </p>
 *
 * @author 
 * @since 2020-11-26
 */
@DS("system")
@Repository("system.MsgSubscribeTblDaoImpl")
public class MsgSubscribeTblDaoImpl extends ServiceImpl<MsgSubscribeTblMapper, MsgSubscribeTbl> implements MsgSubscribeTblDao {

    @Override
    public MsgSubscribeTblMapper getMapper(){
      DynamicDataSourceContextHolder.push("system");
      return this.getBaseMapper();
    }
}
