package com.berg.mp.service.mp;

import com.berg.dao.page.PageInfo;
import com.berg.vo.common.PageInVo;
import com.berg.vo.mp.MsgPublishVo;
import com.berg.vo.mp.in.GetMsgPublishPageInVo;

public interface TemplateMsgPublishService {

    PageInfo<MsgPublishVo> getMsgPublishPage(GetMsgPublishPageInVo input);

    PageInfo<MsgPublishVo> getMsgSubscribePublishPage(PageInVo input);
}
