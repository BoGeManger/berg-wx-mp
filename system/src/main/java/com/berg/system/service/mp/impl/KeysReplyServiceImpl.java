package com.berg.system.service.mp.impl;
import java.time.LocalDateTime;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.berg.common.constant.RedisKeyConstants;
import com.berg.dao.base.DSTransactional;
import com.berg.dao.page.PageInfo;
import com.berg.dao.system.mp.entity.KeysReplyTbl;
import com.berg.dao.system.mp.service.KeysReplyTblDao;
import com.berg.common.exception.UserFriendException;
import com.berg.system.service.AbstractService;
import com.berg.system.service.mp.KeysReplyService;
import com.berg.vo.mp.KeysReplyEditVo;
import com.berg.vo.mp.KeysReplyVo;
import com.berg.vo.mp.in.GetKeysReplyPageInVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class KeysReplyServiceImpl extends AbstractService implements KeysReplyService {

    @Autowired
    KeysReplyTblDao keysReplyTblDao;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * 获取关键字自动回复分页列表
     * @param input
     * @return
     */
    @Override
    public PageInfo<KeysReplyVo> getKeysReplyPage(GetKeysReplyPageInVo input){
        return keysReplyTblDao.page(input,()->{
            LambdaQueryWrapper query = new LambdaQueryWrapper<KeysReplyTbl>().eq(KeysReplyTbl::getAppId,input.getAppId())
                    .like(StringUtils.isNotBlank(input.getKeyContent()),KeysReplyTbl::getKeyContent,input.getKeyContent())
                    .like(StringUtils.isNotBlank(input.getRemark()),KeysReplyTbl::getRemark,input.getRemark());
            return keysReplyTblDao.list(query,KeysReplyVo.class);
        });
    }

    /**
     * 获取关键字自动回复
     * @param id
     * @return
     */
    @Override
    public KeysReplyEditVo getKeysReply(Integer id){
        return keysReplyTblDao.getById(id,KeysReplyEditVo.class);
    }

    /**
     * 新增关键字自动回复
     * @param input
     * @return
     */
    @DSTransactional
    @Override
    public Integer addKeysReply(KeysReplyEditVo input){
        String operator = getUsername();
        Integer id = addOrUpdateKeysReply(input,operator);
        setKeysReplyCache(input);
        return id;
    }

    /**
     * 修改关键字自动回复
     * @param input
     * @return
     */
    @DSTransactional
    @Override
    public Integer updateKeysReply(KeysReplyEditVo input){
        String operator = getUsername();
        Integer id = addOrUpdateKeysReply(input,operator);
        delKeysReplyCache(input.getAppId(),input.getKeyContent());
        setKeysReplyCache(input);
        return id;
    }

    /**
     * 新增或修改关键字自动回复
     * @param input
     * @param operator
     * @return
     */
    Integer addOrUpdateKeysReply(KeysReplyEditVo input,String operator){
        checkKeyContent(input.getId(),input.getAppId(),input.getKeyContent());
        LocalDateTime now = LocalDateTime.now();
        KeysReplyTbl keysReplyTbl = new KeysReplyTbl();
        BeanUtils.copyProperties(input,keysReplyTbl);
        if(keysReplyTbl.getId()==0){
            keysReplyTbl.setCreateTime(now);
            keysReplyTbl.setCreateUser(operator);
            keysReplyTbl.setIsdel(0);
        }
        keysReplyTbl.setModifyTime(now);
        keysReplyTbl.setModifyUser(operator);
        keysReplyTblDao.saveOrUpdateById(keysReplyTbl);
        return keysReplyTbl.getId();
    }

    /**
     * 关键词内容
     * @param id
     * @param appId
     * @param keyContent
     * @return
     */
    void checkKeyContent(Integer id,String appId,String keyContent){
         LambdaQueryWrapper query = new LambdaQueryWrapper<KeysReplyTbl>()
                 .eq(KeysReplyTbl::getKeyContent, keyContent)
                 .eq(KeysReplyTbl::getAppId, appId).ne(KeysReplyTbl::getId, id);
         if (keysReplyTblDao.count(query)>0) {
             throw new UserFriendException("已存在关键词");
         }
    }

    /**
     * 设置关键字自动回复缓存
     * @param input
     */
    void setKeysReplyCache(KeysReplyEditVo input){
        String key = String.format(RedisKeyConstants.Mp.MP_KEYS_REPLY_SET,input.getAppId(),input.getKeyContent());
        JSONObject jsonObject = JSONUtil.parseObj(input);
        jsonObject.remove("id");
        jsonObject.remove("appId");
        jsonObject.remove("keyContent");
        jsonObject.remove("replyContentHtml");
        jsonObject.remove("remark");
        stringRedisTemplate.opsForSet().add(key,jsonObject.toString());
    }

    /**
     * 删除关键字自动回复缓存
     * @param appId
     * @param keyContent
     */
    void delKeysReplyCache(String appId,String keyContent){
        String key = String.format(RedisKeyConstants.Mp.MP_KEYS_REPLY_SET,appId,keyContent);
        stringRedisTemplate.opsForSet().pop(key);
    }

    /**
     * 删除关键字自动回复
     * @param id
     */
    @DSTransactional
    @Override
    public void delKeysReply(Integer id){
        String operator = getUsername();
        KeysReplyTbl keysReplyTbl = keysReplyTblDao.getById(id);
        keysReplyTbl.setId(id);
        keysReplyTbl.setDelTime(LocalDateTime.now());
        keysReplyTbl.setDelUser(operator);
        keysReplyTbl.setIsdel(0);
        keysReplyTblDao.updateById(keysReplyTbl);
        delKeysReplyCache(keysReplyTbl.getAppId(),keysReplyTbl.getKeyContent());
    }
}
