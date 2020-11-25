package com.berg.system.service.mp;

import com.berg.vo.mp.MpTemplateVo;

import java.util.List;

public interface TemplateService {

    List<MpTemplateVo> getTemplateList(String appId);

    void delTemplateCache(String appId);
}
