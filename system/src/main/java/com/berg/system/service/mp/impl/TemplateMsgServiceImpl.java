package com.berg.system.service.mp.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.berg.common.constant.RedisKeyConstants;
import com.berg.dao.page.PageInfo;
import com.berg.dao.system.mp.entity.MsgRecordTbl;
import com.berg.dao.system.mp.service.MsgRecordTblDao;
import com.berg.common.exception.FailException;
import com.berg.system.service.mp.TemplateMsgService;
import com.berg.vo.mp.MpTemplateVo;
import com.berg.vo.mp.MsgRecordVo;
import com.berg.vo.mp.in.GetMsgRecordPageInVo;
import com.berg.wx.mp.utils.WxMpUtil;
import me.chanjar.weixin.mp.bean.template.WxMpTemplate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TemplateMsgServiceImpl implements TemplateMsgService {

    @Resource
    RedisTemplate<String, MpTemplateVo> redisTemplate;

    @Autowired
    MsgRecordTblDao msgRecordTblDao;

    /**
     * 获取模板消息发送分页列表
     * @param input
     * @return
     */
    @Override
    public PageInfo<MsgRecordVo> getMsgRecordPage(GetMsgRecordPageInVo input){
        return msgRecordTblDao.page(input,()->{
            LambdaQueryWrapper query = new LambdaQueryWrapper<MsgRecordTbl>()
                    .eq(StringUtils.isNotBlank(input.getAppId()),MsgRecordTbl::getAppId,input.getAppId())
                    .eq(StringUtils.isNotBlank(input.getTemplateId()),MsgRecordTbl::getTemplateId,input.getTemplateId())
                    .like(StringUtils.isNotBlank(input.getRemark()),MsgRecordTbl::getRemark,input.getRemark())
                    .orderByDesc(MsgRecordTbl::getCreateTime);
            return msgRecordTblDao.list(query,MsgRecordVo.class);
        });
    }
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
