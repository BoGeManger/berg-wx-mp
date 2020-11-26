package com.berg.dao.system.mp.service.impl;

import com.berg.dao.system.mp.entity.MsgPublishTbl;
import com.berg.dao.system.mp.mapper.MsgPublishTblMapper;
import com.berg.dao.system.mp.service.MsgPublishTblDao;
import com.berg.dao.base.ServiceImpl;
import org.springframework.stereotype.Repository;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;

/**
 * <p>
 * 微信公众号消息发布表 服务实现类
 * </p>
 *
 * @author 
 * @since 2020-11-26
 */
@DS("system")
@Repository("system.MsgPublishTblDaoImpl")
public class MsgPublishTblDaoImpl extends ServiceImpl<MsgPublishTblMapper, MsgPublishTbl> implements MsgPublishTblDao {

    @Override
    public MsgPublishTblMapper getMapper(){
      DynamicDataSourceContextHolder.push("system");
      return this.getBaseMapper();
    }
}
