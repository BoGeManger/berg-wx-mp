package com.berg.vo.mp.in;

import com.berg.vo.mp.MpTemplateMiniProgramVo;
import com.berg.vo.mp.MpTemplateDataVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class MpTemplateSendInVo {

    @NotBlank(message = "接收者openid不能为空")
    @ApiModelProperty(value = "接收者openid")
    String toUser;
    @NotBlank(message = "模板ID不能为空")
    @ApiModelProperty(value = "模板ID")
    String templateId;
    @ApiModelProperty(value = "模板跳转链接（海外帐号没有跳转能力）")
    String url;
    @ApiModelProperty(value = "小程序跳转信息")
    MpTemplateMiniProgramVo miniProgram;
    @Valid
    @Size(min = 1, message = "模板消息内容不能小于1")
    @ApiModelProperty(value = "模板消息内容")
    List<MpTemplateDataVo> data;
    @ApiModelProperty(value = "描述")
    String remark;
}
