package com.berg.system.service.mp;

import com.berg.dao.page.PageInfo;
import com.berg.vo.mp.MpTemplateVo;
import com.berg.vo.mp.MsgRecordVo;
import com.berg.vo.mp.in.GetMsgRecordPageInVo;

import java.util.List;

public interface TemplateMsgService {

    PageInfo<MsgRecordVo> getMsgRecordPage(GetMsgRecordPageInVo input);

    List<MpTemplateVo> getTemplateList(String appId);

    void delTemplateCache(String appId);
}
