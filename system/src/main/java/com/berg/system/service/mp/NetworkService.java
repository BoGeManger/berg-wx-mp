package com.berg.system.service.mp;

import com.berg.vo.mp.in.MpNetCheckInVo;
import com.berg.vo.mp.out.MpNetCheckOutVo;

public interface NetworkService {

    MpNetCheckOutVo netCheck(MpNetCheckInVo input);
}
