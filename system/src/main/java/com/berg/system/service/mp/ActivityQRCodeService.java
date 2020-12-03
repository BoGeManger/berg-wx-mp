package com.berg.system.service.mp;

import com.berg.dao.page.PageInfo;
import com.berg.vo.mp.ActivityQRCodeEditVo;
import com.berg.vo.mp.ActivityQRCodeRecordStatisticsVo;
import com.berg.vo.mp.ActivityQRCodeVo;
import com.berg.vo.mp.in.GetActivityQRCodePageInVo;
import com.berg.vo.mp.in.GetActivityRecordStatisticsPageInVo;

public interface ActivityQRCodeService {

    PageInfo<ActivityQRCodeVo> getActivityQRCodePage(GetActivityQRCodePageInVo input);

    ActivityQRCodeEditVo getActivityQRCode(String id);

    String addOrUpdateActivityQRCode(ActivityQRCodeEditVo input);

    String updateActivityQRCode(ActivityQRCodeEditVo input);

    void delActivityQRCode(String id);

    PageInfo<ActivityQRCodeRecordStatisticsVo> getActivityRecordStatisticsPage(GetActivityRecordStatisticsPageInVo input);
}
