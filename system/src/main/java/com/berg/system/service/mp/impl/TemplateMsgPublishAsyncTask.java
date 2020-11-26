package com.berg.system.service.mp.impl;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.berg.dao.system.mp.entity.MsgPublishTbl;
import com.berg.dao.system.mp.entity.MsgSubscribeTbl;
import com.berg.dao.system.mp.service.MsgSubscribeTblDao;
import com.berg.exception.FailException;
import com.berg.wx.mp.utils.WxMpUtil;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class TemplateMsgPublishAsyncTask {

    @Autowired
    TemplateMsgAsyncTask templateMsgAsyncTask;

    @Autowired
    MsgSubscribeTblDao msgSubscribeTblDao;

    /**
     * 异步执行发送模板消息
     * @param msgPublishTbl
     * @param msgSubscribeTbl
     */
    @Transactional
    public void sendAsync(MsgPublishTbl msgPublishTbl, MsgSubscribeTbl msgSubscribeTbl){
        send(msgPublishTbl,msgSubscribeTbl);
    }

    /**
     * 同步执行发送模板消息
     * @param msgPublishTbl
     * @param msgSubscribeTbl
     */
    @Transactional
    public void send(MsgPublishTbl msgPublishTbl, MsgSubscribeTbl msgSubscribeTbl){
        Boolean isExecute = checkExecute(msgPublishTbl,msgSubscribeTbl);
        //未执行状态发送消息
        if(!isExecute){
            //更新订阅记录
            updateSubscribe(msgSubscribeTbl.getId());
            //发送消息
            sendTem(msgPublishTbl,msgSubscribeTbl);
        }
    }

    /**
     * 更新订阅记录
     * @param id
     */
    void updateSubscribe(Integer id){
        LocalDateTime now = LocalDateTime.now();
        MsgSubscribeTbl msgSubscribeTbl = msgSubscribeTblDao.getById(id);
        msgSubscribeTbl.setExecuteNum(msgSubscribeTbl.getExecuteNum()+1);
        msgSubscribeTbl.setExecuteTime(now);
        msgSubscribeTbl.setModifyTime(now);
        msgSubscribeTblDao.updateById(msgSubscribeTbl);
    }

    /**
     * 校验是否已执行判断
     * @param msgPublishTbl
     * @param msgSubscribeTbl
     * @return
     */
    Boolean checkExecute(MsgPublishTbl msgPublishTbl, MsgSubscribeTbl msgSubscribeTbl){
        Boolean isExecute = true;
        if(msgPublishTbl.getOperateType().equals("system")){
            switch (msgPublishTbl.getPublishCycle()){
                case "self"://单次
                    isExecute =checkExecuteSelf(msgPublishTbl,msgSubscribeTbl);
                    break;
                case "day"://每天
                    isExecute =checkExecuteDay(msgPublishTbl,msgSubscribeTbl);
                    break;
                case "week"://每周
                    isExecute =checkExecuteWeek(msgPublishTbl,msgSubscribeTbl);
                    break;
                case "month"://每月
                    isExecute =checkExecuteMonth(msgPublishTbl,msgSubscribeTbl);
                    break;
                case "year"://每年
                    isExecute =checkExecuteYear(msgPublishTbl,msgSubscribeTbl);
                    break;
            }
        }else{
            isExecute = false;
        }
        return isExecute;
    }

    /**
     * 校验周期单次是否已执行
     * @param msgPublishTbl
     * @param msgSubscribeTbl
     * @return
     */
    Boolean checkExecuteSelf(MsgPublishTbl msgPublishTbl, MsgSubscribeTbl msgSubscribeTbl){
        Boolean isExecute = true;
        if(msgSubscribeTbl.getExecuteNum()==0){
            Date now = new Date();
            Date publishDate = DateUtil.parse(msgPublishTbl.getPublishTime());
            if(now.getTime()>=publishDate.getTime()){
                isExecute = false;
            }
        }
        return isExecute;
    }

    /**
     * 校验周期每天是否已执行
     * @param msgPublishTbl
     * @param msgSubscribeTbl
     * @return
     */
    Boolean checkExecuteDay(MsgPublishTbl msgPublishTbl, MsgSubscribeTbl msgSubscribeTbl){
        Boolean isExecute = true;
        //先判断执行次数是否不大于推送限制次数
        if ( msgSubscribeTbl.getExecuteNum()  < msgPublishTbl.getPublishLimit() || msgPublishTbl.getPublishLimit() == -1){
            Date nowTime = new Date();
            //时间拼接
            Date publishTime =  DateUtil.parse(DateUtil.format(nowTime,"yyyy-MM-dd ")+msgPublishTbl.getPublishTime());
            //先对执行时间进行非空判断，防止报空异常错误
            if (msgSubscribeTbl.getExecuteTime() != null) {
                //获得上一次执行时间的年月日
                String yearMonthDay = DateUtil.format(msgSubscribeTbl.getExecuteTime(),"");
                // 若当前系统的年月日等于上一次执行时间的年月日，则表示今天不用执行
                if (DateUtil.format(nowTime,"yyyy-MM-dd").equals(yearMonthDay)){
                    return isExecute;
                }
            }
            if( nowTime.getTime() >= publishTime.getTime()){
                isExecute = false;
            }
        }
        return isExecute;
    }

    /**
     * 校验周期每周是否已执行
     * @param msgPublishTbl
     * @param msgSubscribeTbl
     * @return
     */
    Boolean checkExecuteWeek(MsgPublishTbl msgPublishTbl, MsgSubscribeTbl msgSubscribeTbl){
        Boolean isExecute = true;
        //先判断执行次数是否不大于推送限制次数
        if ( msgSubscribeTbl.getExecuteNum()  < msgPublishTbl.getPublishLimit()|| msgPublishTbl.getPublishLimit() == -1){
            Date nowTime = new Date();
            //判断星期几是否相同
            if(msgPublishTbl.getPublishDate()!=null &&  DateUtil.dayOfWeek(nowTime) == Integer.valueOf(msgPublishTbl.getPublishDate())){
                Date publishTime = DateUtil.parse(DateUtil.format(nowTime,"yyyy-MM-dd ")+msgPublishTbl.getPublishTime());
                //先对执行时间进行非空判断，防止报空异常错误
                if (msgSubscribeTbl.getExecuteTime() != null) {
                    //获得上一次执行时间的年月日
                    String yearMonthDay = DateUtil.format(msgSubscribeTbl.getExecuteTime(),"yyyy-MM-dd");
                    if (DateUtil.format(nowTime,"yyyy-MM-dd").equals(yearMonthDay)){
                        return isExecute;
                    }
                }
                if( nowTime.getTime() >= publishTime.getTime()){
                    isExecute = false;
                }
            }
        }
        return isExecute;
    }

    /**
     * 校验周期每月是否已执行
     * @param msgPublishTbl
     * @param msgSubscribeTbl
     * @return
     */
    Boolean checkExecuteMonth(MsgPublishTbl msgPublishTbl, MsgSubscribeTbl msgSubscribeTbl){
        Boolean isExecute = true;
        //先判断执行次数是否不大于推送限制次数
        if ( msgSubscribeTbl.getExecuteNum()  < msgPublishTbl.getPublishLimit()|| msgPublishTbl.getPublishLimit() == -1){
            Date nowTime = new Date();
            // 判断执行时间段是否为今天（日作对比）
            if(msgPublishTbl.getPublishDate()!=null && DateUtil.format(nowTime,"yyyy-MM").equals(msgPublishTbl.getPublishDate())){
                Date publishTime = DateUtil.parse(DateUtil.format(nowTime,"yyyy-MM-dd ")+msgPublishTbl.getPublishTime());
                //先对执行时间进行非空判断，防止报空异常错误
                if (msgSubscribeTbl.getExecuteTime() != null) {
                    //获得上一次执行时间的年月日
                    String yearMonthDay = DateUtil.format(msgSubscribeTbl.getExecuteTime(),"yyyy-MM-dd");
                    if (DateUtil.format(nowTime,"yyyy-MM-dd").equals(yearMonthDay)){
                        return isExecute;
                    }
                }
                if( nowTime.getTime() >= publishTime.getTime()){
                    isExecute = false;
                }
            }
        }
        return isExecute;
    }

    /**
     * 校验周期每年是否已执行
     * @param msgPublishTbl
     * @param msgSubscribeTbl
     * @return
     */
    Boolean checkExecuteYear(MsgPublishTbl msgPublishTbl, MsgSubscribeTbl msgSubscribeTbl){
        Boolean isExecute = true;
        //先判断执行次数是否不大于推送限制次数
        if ( msgSubscribeTbl.getExecuteNum()  < msgPublishTbl.getPublishLimit()|| msgPublishTbl.getPublishLimit() == -1){
            Date nowTime = new Date();
            // 拼接具体时间 yyyy-MM-dd HH:mm:ss
            Date publishTime = DateUtil.parse(DateUtil.format(nowTime,"yyyy")+"-"+ msgPublishTbl.getPublishTime().trim());
            //先对执行时间进行非空判断，防止报空异常错误
            if (msgSubscribeTbl.getExecuteTime() != null) {
                // 只获得上一次执行时间的年份
                String yearMonthDay = DateUtil.format(msgSubscribeTbl.getExecuteTime(),"yyyy");
                // 与现在的年份进行比较
                if (DateUtil.format(nowTime,"yyyy").equals(yearMonthDay)){
                    return isExecute;
                }
            }
            if( nowTime.getTime() >= publishTime.getTime()){
                isExecute = false;
            }
        }
        return isExecute;
    }

    /**
     * 发送公众号模板消息
     * @param msgPublishTbl
     * @param msgSubscribeTbl
     */
    void sendTem(MsgPublishTbl msgPublishTbl, MsgSubscribeTbl msgSubscribeTbl){
        List<WxMpTemplateData> data = new ArrayList<>();
        List<WxMpTemplateData> list = (List<WxMpTemplateData>) JSONArray.parse(msgPublishTbl.getData());
        for (WxMpTemplateData wxMpTemplateData : list) {
            String value = wxMpTemplateData.getValue();
            if(value.equals("now()")){
                value = DateUtil.format(new Date(), "yyyy年MM月dd日 HH:mm");
            }
            data.add(wxMpTemplateData);
        }
        try{
            WxMpTemplateMessage message = new WxMpTemplateMessage();
            BeanUtils.copyProperties(msgPublishTbl,message);
            WxMpTemplateMessage.MiniProgram miniProgram = new WxMpTemplateMessage.MiniProgram();
            miniProgram.setAppid(msgPublishTbl.getMiniappAppid());
            miniProgram.setPagePath(msgPublishTbl.getMiniappPage());
            miniProgram.setUsePath(msgPublishTbl.getUserMiniappPath()==0?false:true);
            message.setData(data);
            String msgId = WxMpUtil.getService(msgPublishTbl.getAppId()).getTemplateMsgService().sendTemplateMsg(message);
            templateMsgAsyncTask.addMsgRecord(msgPublishTbl.getAppId(),msgSubscribeTbl.getOpenId(),msgId,msgPublishTbl.getTemplateId(),msgPublishTbl.getData(),msgPublishTbl.getUrl(),
                    msgPublishTbl.getMiniappAppid(),msgPublishTbl.getMiniappPage(),msgPublishTbl.getUserMiniappPath(),"订阅模板消息");
        }catch (Exception ex){
            throw new FailException("推送发布订阅模板消息失败:"+ex.getMessage());
        }
    }
}
