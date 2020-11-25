package com.berg.system.service.mp;

import com.berg.vo.mp.MpInterfaceSummaryVo;
import com.berg.vo.mp.in.MpGetInterfaceSummaryInVo;

import java.util.List;

public interface DataCubeService {

    List<MpInterfaceSummaryVo> getInterfaceSummary(MpGetInterfaceSummaryInVo input);

    List<MpInterfaceSummaryVo> getInterfaceSummaryHour(MpGetInterfaceSummaryInVo input);
}
