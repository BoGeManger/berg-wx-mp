package com.berg.dao.system.mp.service;

import com.berg.dao.system.mp.entity.MenuTbl;
import com.berg.dao.base.IService;
import com.berg.dao.system.mp.mapper.MenuTblMapper;

/**
 * <p>
 * 微信公众号菜单表 服务类
 * </p>
 *
 * @author 
 * @since 2020-11-20
 */
public interface MenuTblDao extends IService<MenuTbl> {
   MenuTblMapper getMapper();
}
