package com.berg.vo.mp.out;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MpCreateJsapiSignatureOutVo {

    @ApiModelProperty(value = "公众号的唯一标识")
    String appId;
    @ApiModelProperty(value = "生成签名的随机串")
    String nonceStr;
    @ApiModelProperty(value = "生成签名的时间戳")
    Long timestamp;
    @ApiModelProperty(value = "请求地址")
    String url;
    @ApiModelProperty(value = "签名")
    String signature;
}
