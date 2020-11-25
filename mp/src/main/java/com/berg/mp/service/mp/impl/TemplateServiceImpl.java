package com.berg.mp.service.mp.impl;

import com.berg.constant.RedisKeyConstants;
import com.berg.exception.FailException;
import com.berg.mp.service.base.BaseService;
import com.berg.mp.service.mp.TemplateService;
import com.berg.vo.mp.MpTemplateVo;
import com.berg.vo.mp.in.MpTemplateSendInVo;
import com.berg.wx.mp.utils.WxMpUtil;
import me.chanjar.weixin.mp.bean.template.WxMpTemplate;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TemplateServiceImpl extends BaseService implements TemplateService {

    @Resource
    RedisTemplate<String, MpTemplateVo> redisTemplate;

    /**
     * 获取模板消息列表
     * @return
     */
    @Override
    public List<MpTemplateVo> getTemplateList(){
        String appId = getAppId();
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
     * 发送模板消息
     * @param input
     */
    @Override
    public String send(MpTemplateSendInVo input){
        String msgId = "";
        try {
            WxMpTemplateMessage message = new WxMpTemplateMessage();
            BeanUtils.copyProperties(input,message);
            msgId = WxMpUtil.getService(getAppId()).getTemplateMsgService().sendTemplateMsg(message);
            //新增消息发送记录
        } catch (Exception ex) {
            throw new FailException("调用公众号发送客服文本消息接口sendText失败:" + ex.getMessage());
        }
        return msgId;
    }
}
