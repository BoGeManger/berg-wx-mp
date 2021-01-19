package com.berg.system.service.mp;

import com.berg.dao.page.PageInfo;
import com.berg.vo.mp.MenuVo;
import com.berg.vo.mp.MpMenuVo;
import com.berg.vo.mp.in.GetMenuPageInVo;
import com.berg.vo.mp.in.MpCreateMenuInVo;
import com.berg.vo.mp.out.MpGetMenuOutVo;

public interface MenuService {

    PageInfo<MenuVo> getMenuPage(GetMenuPageInVo input);

    MpMenuVo find(Integer id);

    MpGetMenuOutVo get(String appId);

    void create(MpCreateMenuInVo input);

    MpMenuVo tryMatch(String appId,String userId);

    void delete(String appId);
}
