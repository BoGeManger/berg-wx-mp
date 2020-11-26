package com.berg.system.service.mp.impl;

import com.berg.system.service.mp.AppService;
import com.berg.vo.mp.MpAppVo;
import com.berg.wx.mp.properties.WxMpProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppServiceImpl implements AppService {

    @Autowired
    WxMpProperties properties;

    /**
     * 获取公众号应用列表
     * @return
     */
    @Override
    public List<MpAppVo> getAppList(){
        List<MpAppVo> list = new ArrayList<>();
        properties.getConfigs().forEach(item->{
            MpAppVo maAppVo = new MpAppVo();
            maAppVo.setAppId(item.getAppId());
            maAppVo.setName(item.getName());
        });
        return list;
    }
}
