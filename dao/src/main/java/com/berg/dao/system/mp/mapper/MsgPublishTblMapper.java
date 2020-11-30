package com.berg.dao.system.mp.mapper;

import com.berg.dao.system.mp.entity.MsgPublishTbl;
import com.berg.dao.base.BaseMapper;
import com.berg.vo.mp.MsgPublishVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 微信公众号消息发布表 Mapper 接口
 * </p>
 *
 * @author 
 * @since 2020-11-26
 */
public interface MsgPublishTblMapper extends BaseMapper<MsgPublishTbl> {

    List<MsgPublishVo> getMsgSubscribePublishPage(@Param(value = "appId") String appId, @Param(value = "openId") String openId);
}
