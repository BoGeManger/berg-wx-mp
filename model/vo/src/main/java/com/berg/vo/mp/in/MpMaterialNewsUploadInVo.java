package com.berg.vo.mp.in;

import com.berg.vo.mp.MpNewsArticleVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class MpMaterialNewsUploadInVo {

    @NotBlank(message = "微信公众号appId不能为空")
    @ApiModelProperty(value = "微信公众号appId")
    String appId;
    @ApiModelProperty(value = "描述")
    String remark;
    @ApiModelProperty(value = "图文内容")
    List<MpNewsArticleVo> articles;
}
