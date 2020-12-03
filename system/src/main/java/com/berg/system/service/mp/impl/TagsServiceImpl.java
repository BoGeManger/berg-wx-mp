package com.berg.system.service.mp.impl;

import com.berg.exception.FailException;
import com.berg.system.service.mp.TagsService;
import com.berg.vo.mp.MpUserTagVo;
import com.berg.vo.mp.in.MpBatchTagInVo;
import com.berg.wx.mp.utils.WxMpUtil;
import me.chanjar.weixin.mp.bean.tag.WxUserTag;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagsServiceImpl implements TagsService {

    /**
     * 获取标签列表
     * @param appId
     * @return
     */
    @Override
    public List<MpUserTagVo> getTagsList(String appId){
        List<MpUserTagVo> list = new ArrayList<>();
        try{
            List<WxUserTag> wxUserTagList = WxMpUtil.getService(appId).getUserTagService().tagGet();
            wxUserTagList.forEach(item->{
                MpUserTagVo mpUserTagVo = new MpUserTagVo();
                BeanUtils.copyProperties(item,mpUserTagVo);
                list.add(mpUserTagVo);
            });
        }catch (Exception ex){
            throw new FailException("调用公众号获取标签列表接口getTagsList失败:"+ex.getMessage());
        }
        return list;
    }

    /**
     * 批量为用户打标签
     * @param input
     * @return
     */
    @Override
    public Boolean batchTagging(MpBatchTagInVo input){
        Boolean flag = false;
        try{
            flag = WxMpUtil.getService(input.getAppId()).getUserTagService().batchTagging(input.getTagId(),(String[]) input.getOpenId().toArray());
        }catch (Exception ex){
            throw new FailException("调用公众号批量为用户打标签接口batchTagging失败:"+ex.getMessage());
        }
        return flag;
    }

    /**
     * 批量为用户取消标签
     * @param input
     * @return
     */
    @Override
    public Boolean batchUntagging(MpBatchTagInVo input){
        Boolean flag = false;
        try{

            flag = WxMpUtil.getService(input.getAppId()).getUserTagService().batchUntagging(input.getTagId(),(String[]) input.getOpenId().toArray());
        }catch (Exception ex){
            throw new FailException("调用公众号批量为用户取消标签接口batchUntagging失败:"+ex.getMessage());
        }
        return flag;
    }
}
