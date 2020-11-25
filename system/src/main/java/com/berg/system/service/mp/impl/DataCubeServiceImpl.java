package com.berg.system.service.mp.impl;

import cn.hutool.core.date.DateUtil;
import com.berg.exception.FailException;
import com.berg.system.service.mp.DataCubeService;
import com.berg.vo.mp.MpInterfaceSummaryVo;
import com.berg.vo.mp.in.MpGetInterfaceSummaryInVo;
import com.berg.wx.mp.utils.WxMpUtil;
import jodd.bean.BeanUtil;
import me.chanjar.weixin.mp.bean.datacube.WxDataCubeInterfaceResult;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DataCubeServiceImpl implements DataCubeService {

    /**
     * 获取接口分析数据
     * @param input
     */
    @Override
    public List<MpInterfaceSummaryVo> getInterfaceSummary(MpGetInterfaceSummaryInVo input){
        List<MpInterfaceSummaryVo> list = new ArrayList<>();
        try{
            Date beginDate = DateUtil.parseDate(input.getBeginDate());
            Date endDate = DateUtil.parseDate(input.getEndDate());
            List<WxDataCubeInterfaceResult> wxDataCubeInterfaceResultList = WxMpUtil.getService(input.getAppId()).getDataCubeService().getInterfaceSummary(beginDate,endDate);
            wxDataCubeInterfaceResultList.forEach(item->{
                MpInterfaceSummaryVo mpInterfaceSummaryVo = new MpInterfaceSummaryVo();
                BeanUtils.copyProperties(item,mpInterfaceSummaryVo);
                list.add(mpInterfaceSummaryVo);
            });
        }catch (Exception ex){
            throw new FailException("调用微信公众号获取接口分析数据接口getInterfaceSummary失败:"+ex.getMessage());
        }
        return list;
    }

    /**
     * 获取接口分析分时数据
     * @param input
     */
    @Override
    public List<MpInterfaceSummaryVo> getInterfaceSummaryHour(MpGetInterfaceSummaryInVo input){
        List<MpInterfaceSummaryVo> list = new ArrayList<>();
        try{
            Date beginDate = DateUtil.parseDate(input.getBeginDate());
            Date endDate = DateUtil.parseDate(input.getEndDate());
            List<WxDataCubeInterfaceResult> wxDataCubeInterfaceResultList = WxMpUtil.getService(input.getAppId()).getDataCubeService().getInterfaceSummaryHour(beginDate,endDate);
            wxDataCubeInterfaceResultList.forEach(item->{
                MpInterfaceSummaryVo mpInterfaceSummaryVo = new MpInterfaceSummaryVo();
                BeanUtils.copyProperties(item,mpInterfaceSummaryVo);
                list.add(mpInterfaceSummaryVo);
            });
        }catch (Exception ex){
            throw new FailException("调用微信公众号获取接口分析分时数据接口getInterfaceSummaryHour失败:"+ex.getMessage());
        }
        return list;
    }
}
