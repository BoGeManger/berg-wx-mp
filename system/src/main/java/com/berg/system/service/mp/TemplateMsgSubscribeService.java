package com.berg.system.service.mp;

import com.berg.dao.page.PageInfo;
import com.berg.vo.mp.MsgSubscribeVo;
import com.berg.vo.mp.in.GetMsgSubscribePageInVo;

public interface TemplateMsgSubscribeService {

    PageInfo<MsgSubscribeVo> getMsgSubscribePage(GetMsgSubscribePageInVo input);
}
