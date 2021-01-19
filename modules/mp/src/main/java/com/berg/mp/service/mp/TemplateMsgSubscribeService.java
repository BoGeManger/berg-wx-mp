package com.berg.mp.service.mp;

import com.berg.vo.mp.in.TemplateSubscribeInVo;

public interface TemplateMsgSubscribeService {

    void subscribe(TemplateSubscribeInVo input);

    void unsubscribe(String publishId);
}
