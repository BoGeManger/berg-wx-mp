package com.berg.system.service.mp;

import com.berg.dao.page.PageInfo;
import com.berg.vo.mp.ShortUrlVo;
import com.berg.vo.mp.in.GetShortUrlPageInVo;
import com.berg.vo.mp.in.MpCreateShortUrlInVo;

public interface ShortUrlService {

    PageInfo<ShortUrlVo> getShortUrlPage(GetShortUrlPageInVo input);

    String create(MpCreateShortUrlInVo input);
}
