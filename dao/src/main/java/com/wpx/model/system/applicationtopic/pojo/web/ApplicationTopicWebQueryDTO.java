package com.wpx.model.system.applicationtopic.pojo.web;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author wupengxiao
 * @since 2021-06-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ApplicationTopicWebQueryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "应用主题ID")
    private Integer applicationTopicId;

    @ApiModelProperty(value = "应用ID")
    private Integer applicationId;

    @ApiModelProperty(value = "主题名称")
    private String topic;

    @ApiModelProperty(value = "主题消息图标")
    private String icon;

    @ApiModelProperty(value = "主题消息颜色")
    private String color;

    @ApiModelProperty(value = "主题消息标题")
    private String title;

    @ApiModelProperty(value = "主题消息内容")
    private String body;

}
