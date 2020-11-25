package com.berg.system.service.mp.impl;

import com.berg.constant.RedisKeyConstants;
import com.berg.exception.FailException;
import com.berg.system.service.mp.TemplateService;
import com.berg.vo.mp.MpTemplateVo;
import com.berg.wx.mp.utils.WxMpUtil;
import me.chanjar.weixin.mp.bean.template.WxMpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TemplateServiceImpl implements TemplateService {

    @Resource
    RedisTemplate<String, MpTemplateVo> redisTemplate;

    /**
     * 获取模板消息列表
     * @param appId
     * @return
     */
    @Override
    public List<MpTemplateVo> getTemplateList(String appId){
        String key = String.format(RedisKeyConstants.Mp.MP_TEMPLATE_LIST,appId);
        List<MpTemplateVo> list = redisTemplate.opsForList().range(key, 0, -1);
        if (list.size() == 0) {
            try {
                List<WxMpTemplate> wxMpTemplateList = WxMpUtil.getService(appId).getTemplateMsgService().getAllPrivateTemplate();
                wxMpTemplateList.forEach(item -> {
                    MpTemplateVo mpTemplateVo = new MpTemplateVo();
                    BeanUtils.copyProperties(item, mpTemplateVo);
                    list.add(mpTemplateVo);
                });
            } catch (Exception ex) {
                throw new FailException("调用微信公众号获取模板消息列表接口getTemplateList失败:" + ex.getMessage());
            }
        }
        return list;
    }

    /**
     * 删除模板缓存
     * @param appId
     */
    @Override
    public void delTemplateCache(String appId){
        String key = String.format(RedisKeyConstants.Mp.MP_TEMPLATE_LIST,appId);
        redisTemplate.delete(key);
    }
}
