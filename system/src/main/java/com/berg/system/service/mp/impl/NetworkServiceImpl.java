package com.berg.system.service.mp.impl;

import com.berg.exception.FailException;
import com.berg.system.service.mp.NetworkService;
import com.berg.vo.mp.in.MpNetCheckInVo;
import com.berg.vo.mp.out.MpNetCheckOutVo;
import com.berg.wx.mp.utils.WxMpUtil;
import me.chanjar.weixin.common.bean.WxNetCheckResult;
import org.springframework.stereotype.Service;

@Service
public class NetworkServiceImpl implements NetworkService {

    /**
     * 网络检测
     * @param input
     * @return
     */
    public MpNetCheckOutVo netCheck(MpNetCheckInVo input){
        MpNetCheckOutVo result = new MpNetCheckOutVo();
        try{
            WxNetCheckResult wxNetCheckResult = WxMpUtil.getService(input.getAppId()).netCheck(input.getAction(),input.getAppId());
        }catch (Exception ex){
            throw new FailException("调用公众号网络检测接口netCheck失败:"+ex.getMessage());
        }
        return result;
    }
}
