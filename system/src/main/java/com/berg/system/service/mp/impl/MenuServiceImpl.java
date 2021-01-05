package com.berg.system.service.mp.impl;
import java.time.LocalDateTime;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.berg.dao.base.DSTransactional;
import com.berg.dao.page.PageInfo;
import com.berg.dao.system.mp.entity.MenuTbl;
import com.berg.dao.system.mp.service.MenuTblDao;
import com.berg.common.exception.FailException;
import com.berg.common.exception.ParamException;
import com.berg.system.service.AbstractService;
import com.berg.system.service.mp.MenuService;
import com.berg.vo.mp.MenuVo;
import com.berg.vo.mp.MpMenuVo;
import com.berg.vo.mp.in.GetMenuPageInVo;
import com.berg.vo.mp.in.MpCreateMenuInVo;
import com.berg.vo.mp.out.MpGetMenuOutVo;
import com.berg.wx.utils.WxMpUtil;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.mp.bean.menu.WxMpMenu;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl extends AbstractService implements MenuService {

    @Autowired
    MenuTblDao menuTblDao;

    /**
     * 获取菜单记录分页列表
     * @param input
     * @return
     */
    @Override
    public PageInfo<MenuVo> getMenuPage(GetMenuPageInVo input){
        return menuTblDao.page(input,()->{
            LambdaQueryWrapper<MenuTbl> query = new LambdaQueryWrapper<MenuTbl>()
                    .eq(MenuTbl::getAppId,input.getAppId())
                    .like(StringUtils.isNotBlank(input.getRemark()),MenuTbl::getRemark,input.getRemark())
                    .orderByDesc(MenuTbl::getCreateTime);
            return menuTblDao.list(query,MenuVo.class);
        });
    }

    /**
     * 获取菜单记录明细
     * @param id
     * @return
     */
    @Override
    public MpMenuVo find(Integer id){
        MenuTbl menuTbl = menuTblDao.getById(id);
        if(menuTbl==null){
            throw new ParamException("菜单记录为空");
        }
        return JSONUtil.toBean(menuTbl.getMenu(),MpMenuVo.class);
    }

    /**
     * 获取公众号菜单
     * @param appId
     * @return
     */
    @Override
    public MpGetMenuOutVo get(String appId){
        MpGetMenuOutVo result = new MpGetMenuOutVo();
        try{
            WxMpMenu wxMpMenu = WxMpUtil.getService(appId).getMenuService().menuGet();
            BeanUtils.copyProperties(wxMpMenu,result);
        }catch (Exception ex){
            throw new FailException("调用获取公众号菜单接口get失败:"+ex.getMessage());
        }
        return result;
    }

    /**
     * 新增公众号菜单
     * @param input
     */
    @DSTransactional
    @Override
    public void create(MpCreateMenuInVo input){
        String menu = JSONUtil.toJsonStr(input.getMenu());
        MenuTbl menuTbl = new MenuTbl();
        menuTbl.setAppId(input.getAppId());
        menuTbl.setMenu(menu);
        menuTbl.setRemark(input.getRemark());
        menuTbl.setCreateTime(LocalDateTime.now());
        menuTbl.setCreateUser(getUsername());
        menuTblDao.save(menuTbl);
        try{
            WxMpUtil.getService(input.getAppId()).getMenuService().menuCreate(menu);
        }catch (Exception ex){
            throw new FailException("调用新增公众号菜单接口create失败:"+ex.getMessage());
        }
    }

    /**
     * 测试用户个性化菜单匹配结果
     * @param appId
     * @param userId
     * @return
     */
    @Override
    public MpMenuVo tryMatch(String appId,String userId){
        MpMenuVo result = new MpMenuVo();
        try{
            WxMenu wxMenu = WxMpUtil.getService(appId).getMenuService().menuTryMatch(userId);
            BeanUtils.copyProperties(wxMenu,result);
        }catch (Exception ex){
            throw new FailException("调用公众号测试用户个性化菜单匹配结果接口tryMatch失败:"+ex.getMessage());
        }
        return result;
    }

    /**
     * 删除公众号菜单
     * @param appId
     */
    @Override
    public void delete(String appId){
        try{
            WxMpUtil.getService(appId).getMenuService().menuDelete();
        }catch (Exception ex){
            throw new FailException("调用公众号删除菜单接口delete失败:"+ex.getMessage());
        }
    }
}
