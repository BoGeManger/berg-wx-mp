package com.berg.system.service.mp.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.berg.dao.page.PageInfo;
import com.berg.dao.system.mp.entity.MsgPublishTbl;
import com.berg.dao.system.mp.entity.MsgSubscribeTbl;
import com.berg.dao.system.mp.service.MsgPublishTblDao;
import com.berg.dao.system.mp.service.MsgSubscribeTblDao;
import com.berg.system.auth.JWTUtil;
import com.berg.system.service.mp.TemplateMsgPublishService;
import com.berg.utils.SnowflakeIdWorker;
import com.berg.vo.mp.MsgPublishEditVo;
import com.berg.vo.mp.MsgPublishVo;
import com.berg.vo.mp.in.GetMsgPublishPageInVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TemplateMsgPublishServiceImpl implements TemplateMsgPublishService {

    @Autowired
    JWTUtil jwtUtil;

    @Autowired
    TemplateMsgPublishAsyncTask templateMsgPublishAsyncTask;

    @Autowired
    MsgPublishTblDao msgPublishTblDao;
    @Autowired
    MsgSubscribeTblDao msgSubscribeTblDao;

    /**
     * 获取模板消息发布分页列表
     * @param input
     * @return
     */
    @Override
    public PageInfo<MsgPublishVo> getMsgPublishPage(GetMsgPublishPageInVo input){
        return msgPublishTblDao.page(input,()->{
            LambdaQueryWrapper query = new LambdaQueryWrapper<MsgPublishTbl>()
                    .eq(MsgPublishTbl::getAppId,input.getAppId())
                    .eq(input.getStatus()!=null,MsgPublishTbl::getStatus,input.getStatus())
                    .like(StringUtils.isNotBlank(input.getRemark()),MsgPublishTbl::getRemark,input.getRemark())
                    .orderByDesc(MsgPublishTbl::getCreateTime);
            return msgPublishTblDao.list(query,MsgPublishVo.class);
        });
    }

    /**
     * 获取模板消息发布
     * @param id
     * @return
     */
    @Override
    public MsgPublishEditVo getMsgPublish(String id){
        MsgPublishEditVo result = new MsgPublishEditVo();
        MsgPublishTbl msgPublishTbl = msgPublishTblDao.getById(id);
        BeanUtils.copyProperties(msgPublishTbl,result);
        LambdaQueryWrapper query = new QueryWrapper<MsgSubscribeTbl>().select("open_id").lambda()
                .eq(MsgSubscribeTbl::getAppId,msgPublishTbl.getAppId())
                .eq(MsgSubscribeTbl::getPublishId,msgPublishTbl.getId())
                .eq(MsgSubscribeTbl::getStatus,0);
        List<String> openIds = msgSubscribeTblDao.listObjs(query);
        result.setOpenIds(openIds);
        return result;
    }

    /**
     * 新增模板消息发布
     * @param input
     * @return
     */
    @Override
    public String addMsgPublish(MsgPublishEditVo input){
        return save(input);
    }

    /**
     * 修改模板消息发布
     * @param input
     * @return
     */
    @Override
    public String updateMsgPublish(MsgPublishEditVo input){
        return save(input);
    }

    /**
     * 新增或修改模板消息发布
     * @param input
     * @return
     */
    String save(MsgPublishEditVo input){
        LocalDateTime now = LocalDateTime.now();
        String operator = jwtUtil.getUsername();
        MsgPublishTbl msgPublishTbl = new MsgPublishTbl();
        BeanUtils.copyProperties(input,msgPublishTbl);
        if(input.getAppId().equals("0")){
            msgPublishTbl.setId(SnowflakeIdWorker.getStrUid());
            msgPublishTbl.setCreateUser(operator);
            msgPublishTbl.setCreateTime(now);
        }
        msgPublishTbl.setModifyUser(operator);
        msgPublishTbl.setModifyTime(now);
        msgPublishTblDao.saveOrUpdateById(msgPublishTbl);
        return msgPublishTbl.getId();
    }

    /**
     * 立即发送消息
     * @param id
     */
    @Override
    @Transactional
    public void sendMessage(String id) {
        String operator = jwtUtil.getUsername();
        LocalDateTime now = LocalDateTime.now();
        //消息发布更新
        MsgPublishTbl msgPublishTbl = new MsgPublishTbl();
        msgPublishTbl.setId(id);
        msgPublishTbl.setPublishTime(DateUtil.format(now, "yyyy-MM-dd HH:mm:ss"));
        msgPublishTbl.setModifyUser(operator);
        msgPublishTbl.setModifyTime(now);
        msgPublishTblDao.updateById(msgPublishTbl);
        //订阅查询
        LambdaQueryWrapper query = new LambdaQueryWrapper<MsgSubscribeTbl>()
                .eq(MsgSubscribeTbl::getPublishId,id)
                .eq(MsgSubscribeTbl::getStatus,0);
        List<MsgSubscribeTbl> mpmsgSubscribeTblList = msgSubscribeTblDao.list(query);
        //消息发送
        mpmsgSubscribeTblList.forEach(k -> {
            templateMsgPublishAsyncTask.sendAsync(msgPublishTbl,k);
        });
    }
}
