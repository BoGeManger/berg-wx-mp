package com.berg.system.task;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.berg.dao.system.mp.entity.MsgPublishTbl;
import com.berg.dao.system.mp.entity.MsgSubscribeTbl;
import com.berg.dao.system.mp.service.MsgPublishTblDao;
import com.berg.dao.system.mp.service.MsgSubscribeTblDao;
import com.berg.system.service.mp.impl.TemplateMsgPublishAsyncTask;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
public class TemplateMsgPublishJob implements Job {


    @Autowired
    TemplateMsgPublishAsyncTask templateMsgPublishAsyncTask;

    @Autowired
    MsgPublishTblDao msgPublishTblDao;
    @Autowired
    MsgSubscribeTblDao msgSubscribeTblDao;

    /**
     * 定时执行订阅模板消息任务(每秒一次)
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info(String.format("模板消息任务TemplateMsgPublishJob 时间：%s",LocalDateTime.now()));
        LocalDateTime now = LocalDateTime.now();
        //发布查询
        LambdaQueryWrapper publishQuery = new LambdaQueryWrapper<MsgPublishTbl>()
                .eq(MsgPublishTbl::getOperateType,1)
                .eq(MsgPublishTbl::getStatus,0);
        List<MsgPublishTbl> msgPublishTblList = msgPublishTblDao.list(publishQuery);
        msgPublishTblList.forEach(msgPublishTbl->{
            msgPublishTbl.setPublishTime(DateUtil.format(now, "yyyy-MM-dd HH:mm:ss"));
            msgPublishTbl.setModifyUser("system");
            msgPublishTbl.setModifyTime(now);
            msgPublishTblDao.updateById(msgPublishTbl);
            //订阅查询
            LambdaQueryWrapper subscribeQuery = new LambdaQueryWrapper<MsgSubscribeTbl>()
                    .eq(MsgSubscribeTbl::getPublishId,msgPublishTbl.getId())
                    .eq(MsgSubscribeTbl::getStatus,0);
            List<MsgSubscribeTbl> mpmsgSubscribeTblList = msgSubscribeTblDao.list(subscribeQuery);
            //消息发送
            mpmsgSubscribeTblList.forEach(k -> {
                templateMsgPublishAsyncTask.send(msgPublishTbl,k);
            });
        });
    }
}
