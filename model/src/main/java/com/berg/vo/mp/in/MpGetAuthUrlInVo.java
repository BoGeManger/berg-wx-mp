package com.berg.vo.mp.in;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MpGetAuthUrlInVo {

    @NotBlank(message = "用户授权完成后的重定向链接不能为空")
    @ApiModelProperty(value = "用户授权完成后的重定向链接，无需urlencode, 方法内会进行encode")
    String redirectUri;
    @NotBlank(message = "应用授权作用域不能为空")
    @ApiModelProperty(value = "应用授权作用域，snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid），snsapi_userinfo （弹出授权页面，可通过openid拿到昵称、性别、所在地。并且， 即使在未关注的情况下，只要用户授权，也能获取其信息 ）")
    String scope;
    @ApiModelProperty(value = "重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节")
    String state;
}
