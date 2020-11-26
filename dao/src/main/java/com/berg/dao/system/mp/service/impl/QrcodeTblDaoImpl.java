package com.berg.dao.system.mp.service.impl;

import com.berg.dao.system.mp.entity.QrcodeTbl;
import com.berg.dao.system.mp.mapper.QrcodeTblMapper;
import com.berg.dao.system.mp.service.QrcodeTblDao;
import com.berg.dao.base.ServiceImpl;
import org.springframework.stereotype.Repository;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;

/**
 * <p>
 * 微信公众号二维码表 服务实现类
 * </p>
 *
 * @author 
 * @since 2020-11-26
 */
@DS("system")
@Repository("system.QrcodeTblDaoImpl")
public class QrcodeTblDaoImpl extends ServiceImpl<QrcodeTblMapper, QrcodeTbl> implements QrcodeTblDao {

    @Override
    public QrcodeTblMapper getMapper(){
      DynamicDataSourceContextHolder.push("system");
      return this.getBaseMapper();
    }
}
