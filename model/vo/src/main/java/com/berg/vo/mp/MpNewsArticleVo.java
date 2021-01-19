package com.berg.vo.mp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MpNewsArticleVo {

    @ApiModelProperty(value = "(必填) 图文消息缩略图的media_id，可以在基础支持-上传多媒体文件接口中获得")
    String thumbMediaId;
    @ApiModelProperty(value = "图文消息的封面url")
    String thumbUrl;
    @ApiModelProperty(value = "图文消息的作者")
    String author;
    @ApiModelProperty(value = "(必填) 图文消息的标题")
    String title;
    @ApiModelProperty(value = "在图文消息页面点击“阅读原文”后的页面链接")
    String contentSourceUrl;
    @ApiModelProperty(value = "(必填) 图文消息页面的内容，支持HTML标签")
    String content;
    @ApiModelProperty(value = "图文消息的描述")
    String digest;
    @ApiModelProperty(value = "是否显示封面，true为显示，false为不显示")
    Boolean showCoverPic;
    @ApiModelProperty(value = "点击图文消息跳转链接")
    String url;
    @ApiModelProperty(value = "是否打开评论，0不打开，1打开")
    Boolean needOpenComment;
    @ApiModelProperty(value = "是否粉丝才可评论，0所有人可评论，1粉丝才可评论")
    Boolean onlyFansCanComment;
}
