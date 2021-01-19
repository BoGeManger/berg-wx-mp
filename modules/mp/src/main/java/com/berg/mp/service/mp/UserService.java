package com.berg.mp.service.mp;

import com.berg.vo.mp.in.MpRegisterInVo;
import com.berg.vo.mp.out.MpOAuthUserInfoOutVo;
import com.berg.vo.mp.out.MpUserInfoOutVo;

public interface UserService {

    void getVerifyCode(String phone);

    void register(MpRegisterInVo input);

    MpUserInfoOutVo userInfo();

    MpOAuthUserInfoOutVo oauthUserInfo();
}
