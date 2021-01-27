package com.berg.system.service.mp.impl;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.berg.common.constant.RedisKeyConstants;
import com.berg.common.utils.SnowflakeIdWorker;
import com.berg.dao.base.DSTransactional;
import com.berg.dao.page.PageInfo;
import com.berg.dao.system.mp.entity.ActivityQrcodeEventTbl;
import com.berg.dao.system.mp.entity.ActivityQrcodeTbl;
import com.berg.dao.system.mp.service.ActivityQrcodeEventTblDao;
import com.berg.dao.system.mp.service.ActivityQrcodeRecordTblDao;
import com.berg.dao.system.mp.service.ActivityQrcodeTblDao;
import com.berg.auth.system.service.AbstractService;
import com.berg.system.service.mp.ActivityQRCodeService;
import com.berg.vo.mp.ActivityQRCodeEditVo;
import com.berg.vo.mp.ActivityQRCodeEventVo;
import com.berg.vo.mp.ActivityQRCodeRecordStatisticsVo;
import com.berg.vo.mp.ActivityQRCodeVo;
import com.berg.vo.mp.in.GetActivityQRCodePageInVo;
import com.berg.vo.mp.in.GetActivityRecordStatisticsPageInVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ActivityQRCodeServiceImpl extends AbstractService implements ActivityQRCodeService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    ActivityQrcodeTblDao activityQrcodeTblDao;
    @Autowired
    ActivityQrcodeEventTblDao activityQrcodeEventTblDao;
    @Autowired
    ActivityQrcodeRecordTblDao activityQrcodeRecordTblDao;

    /**
     * 获取活动二维码分页列表
     * @param input
     * @return
     */
    @Override
    public PageInfo<ActivityQRCodeVo> getActivityQRCodePage(GetActivityQRCodePageInVo input){
        return activityQrcodeTblDao.page(input,()->{
            LambdaQueryWrapper query = new LambdaQueryWrapper<ActivityQrcodeTbl>()
                    .eq(ActivityQrcodeTbl::getAppId,input.getAppId())
                    .like(StringUtils.isNotBlank(input.getName()),ActivityQrcodeTbl::getName,input.getName());
            return activityQrcodeTblDao.list(query,ActivityQRCodeVo.class);
        });
    }

    /**
     * 获取活动二维码
     * @param id
     * @return
     */
    @Override
    public ActivityQRCodeEditVo getActivityQRCode(String id){
        ActivityQRCodeEditVo result = new ActivityQRCodeEditVo();
        ActivityQrcodeTbl activityQrcodeTbl = activityQrcodeTblDao.getById(id);
        BeanUtils.copyProperties(activityQrcodeTbl,result);
        LambdaQueryWrapper query = new LambdaQueryWrapper<ActivityQrcodeEventTbl>()
                .eq(ActivityQrcodeEventTbl::getAppId,activityQrcodeTbl.getAppId())
                .eq(ActivityQrcodeEventTbl::getActivityId,activityQrcodeTbl.getId());
        List<ActivityQRCodeEventVo> events = activityQrcodeEventTblDao.list(query,ActivityQRCodeEventVo.class);
        result.setEvents(events);
        return result;
    }

    /**
     * 新增活动二维码
     * @param input
     * @return
     */
    @DSTransactional
    @Override
    public String addOrUpdateActivityQRCode(ActivityQRCodeEditVo input){
        String operator = getUsername();
        return addOrUpdateActivityQRCode(input,operator);
    }

    /**
     * 修改活动二维码
     * @param input
     * @return
     */
    @DSTransactional
    @Override
    public String updateActivityQRCode(ActivityQRCodeEditVo input){
        String operator = getUsername();
        return addOrUpdateActivityQRCode(input,operator);
    }

    /**
     * 新增或修改微信公众号活动二维码
     * @param input
     * @param operator
     * @return
     */
    String addOrUpdateActivityQRCode(ActivityQRCodeEditVo input,String operator){
        LocalDateTime now = LocalDateTime.now();
        ActivityQrcodeTbl activityQrcodeTbl = new ActivityQrcodeTbl();
        BeanUtils.copyProperties(input,activityQrcodeTbl);
        if(input.getId().equals("0")){
            activityQrcodeTbl.setId(SnowflakeIdWorker.getStrUid());
            activityQrcodeTbl.setCreateTime(now);
            activityQrcodeTbl.setCreateUser(operator);
            activityQrcodeTbl.setIsdel(0);
        }
        activityQrcodeTbl.setModifyTime(now);
        activityQrcodeTbl.setModifyUser(operator);
        activityQrcodeTblDao.saveOrUpdateById(activityQrcodeTbl);
        //新增或修改事件明细
        addOrUpdateActivityQRCodeEvent(input.getEvents(),input.getAppId(),activityQrcodeTbl.getId(),activityQrcodeTbl.getCode());
        return activityQrcodeTbl.getId();
    }

    /**
     * 新增或修改微信公众号活动二维码事件明细
     * @param input
     * @param appId
     * @param activityId
     * @param code
     */
    void addOrUpdateActivityQRCodeEvent(List<ActivityQRCodeEventVo> input,String appId,String activityId,String code){
        List<ActivityQrcodeEventTbl> list = new ArrayList<>();
        for(ActivityQRCodeEventVo item:input){
            ActivityQrcodeEventTbl activityQrcodeEventTbl = new ActivityQrcodeEventTbl();
            BeanUtils.copyProperties(item,activityQrcodeEventTbl);
            if(item.getId() ==0){
                activityQrcodeEventTbl.setAppId(appId);
                activityQrcodeEventTbl.setActivityId(activityId);
            }
            list.add(activityQrcodeEventTbl);
        }
        activityQrcodeEventTblDao.saveOrUpdateBatchById(list);
        //设置活动二维码事件缓存
        list.forEach(item->{
            setActivityQRCodeCache(item.getAppId(),code,item.getEvent(),item.getKeys());
        });
    }

    /**
     * 设置活动二维码事件缓存
     * @param appId
     * @param code
     * @param event
     * @param value
     */
    void setActivityQRCodeCache(String appId,String code,String event,String value){
        String key = String.format(RedisKeyConstants.Mp.MP_ACTIVITY_QRCODE_EVENT_SET, appId, code,event);
        stringRedisTemplate.opsForValue().set(key,value);
    }

    /**
     * 删除活动二维码
     * @param id
     */
    @Override
    public void delActivityQRCode(String id){
        String operator = getUsername();
        ActivityQrcodeTbl activityQrcodeTbl = activityQrcodeTblDao.getById(id);
        activityQrcodeTbl.setDelTime(LocalDateTime.now());
        activityQrcodeTbl.setDelUser(operator);
        activityQrcodeTbl.setIsdel(1);
        activityQrcodeTblDao.updateById(activityQrcodeTbl);
        //删除活动二维码事件缓存
        delActivityQRCodeCache(activityQrcodeTbl.getAppId(),activityQrcodeTbl.getCode());
    }

    /**
     * 删除活动二维码事件缓存
     * @param appId
     * @param code
     */
    void delActivityQRCodeCache(String appId,String code){
        String prefix = String.format("mp_activity_qrcode_event_set_%s_%s_*",appId,code);
        Set<String> keys = stringRedisTemplate.keys(prefix);
        for(String key : keys){
            stringRedisTemplate.delete(key);
        }
    }

    /**
     * 获取活动二维码记录统计分页列表
     * @param input
     * @return
     */
    @Override
    public PageInfo<ActivityQRCodeRecordStatisticsVo> getActivityRecordStatisticsPage(GetActivityRecordStatisticsPageInVo input){
        return activityQrcodeRecordTblDao.page(input,()->{
          return activityQrcodeRecordTblDao.getMapper().listActivityRecordStatistics(input.getAppId(),input.getName());
        });
    }
}
