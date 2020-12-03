package com.berg.dao.system.mp.mapper;

import com.berg.dao.system.mp.entity.ActivityQrcodeRecordTbl;
import com.berg.dao.base.BaseMapper;
import com.berg.vo.mp.ActivityQRCodeRecordStatisticsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2020-12-03
 */
public interface ActivityQrcodeRecordTblMapper extends BaseMapper<ActivityQrcodeRecordTbl> {

    List<ActivityQRCodeRecordStatisticsVo> listActivityRecordStatistics(@Param(value = "appId") String appId, @Param(value = "name") String name);
}
