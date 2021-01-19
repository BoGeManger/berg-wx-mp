package com.berg.mp.service.mp;

import com.berg.vo.mp.MpTemplateVo;
import com.berg.vo.mp.in.MpTemplateSendInVo;

import java.util.List;

public interface TemplateMsgService {

    List<MpTemplateVo> getTemplateList();

    String send(MpTemplateSendInVo input);
}
