package com.berg.vo.mp.out;

import com.berg.vo.mp.MpNetCheckDnsInfoVo;
import com.berg.vo.mp.MpNetCheckPingInfoVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class MpNetCheckOutVo {

    @ApiModelProperty(value = "dns结果列表")
    List<MpNetCheckDnsInfoVo> dnsInfos;
    @ApiModelProperty(value = "ping结果列表")
    List<MpNetCheckPingInfoVo> pingInfos;
}
