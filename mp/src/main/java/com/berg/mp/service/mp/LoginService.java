package com.berg.mp.service.mp;

import com.berg.vo.mp.in.MpGetAuthUrlInVo;
import com.berg.vo.mp.in.MpLoginInVo;
import com.berg.vo.mp.in.MpRefreshInVo;
import com.berg.vo.mp.out.MpLoginOutVo;

public interface LoginService {

    String getAuthUrl(MpGetAuthUrlInVo input);

    MpLoginOutVo login(MpLoginInVo input);

    MpLoginOutVo refresh(MpRefreshInVo input);

    void logout();
}
