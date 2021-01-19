package com.berg.vo.mp.in;

import com.berg.vo.mp.MpNewsArticleVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MpMaterialNewsUpdateInVo {

    @NotBlank(message = "微信公众号appId不能为空")
    @ApiModelProperty(value = "微信公众号appId")
    String appId;
    @ApiModelProperty(value = "媒体id")
    String mediaId;
    @ApiModelProperty(value = "要更新的文章在图文消息中的位置（多图文消息时，此字段才有意义），第一篇为0")
    Integer index;
    @ApiModelProperty(value = "图文内容")
    MpNewsArticleVo articles;
}
