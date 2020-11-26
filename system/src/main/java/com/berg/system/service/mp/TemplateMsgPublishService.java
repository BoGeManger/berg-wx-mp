package com.berg.system.service.mp;

import com.berg.dao.page.PageInfo;
import com.berg.vo.mp.MsgPublishEditVo;
import com.berg.vo.mp.MsgPublishVo;
import com.berg.vo.mp.in.GetMsgPublishPageInVo;

public interface TemplateMsgPublishService {

    PageInfo<MsgPublishVo> getMsgPublishPage(GetMsgPublishPageInVo input);

    MsgPublishEditVo getMsgPublish(String id);

    String addMsgPublish(MsgPublishEditVo input);

    String updateMsgPublish(MsgPublishEditVo input);

    void sendMessage(String id);
}
