package com.berg.dao.system.mp.service.impl;

import com.berg.dao.system.mp.entity.MenuTbl;
import com.berg.dao.system.mp.mapper.MenuTblMapper;
import com.berg.dao.system.mp.service.MenuTblDao;
import com.berg.dao.base.ServiceImpl;
import org.springframework.stereotype.Repository;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;

/**
 * <p>
 * 微信公众号菜单表 服务实现类
 * </p>
 *
 * @author 
 * @since 2020-11-20
 */
@DS("system")
@Repository("system.MenuTblDaoImpl")
public class MenuTblDaoImpl extends ServiceImpl<MenuTblMapper, MenuTbl> implements MenuTblDao {

    @Override
    public MenuTblMapper getMapper(){
      DynamicDataSourceContextHolder.push("system");
      return this.getBaseMapper();
    }
}
