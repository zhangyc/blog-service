package com.minzheng.blog.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author 11921
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "剧本VO")
public class ScreenplayVO {

    /**
     * 游戏名称
     */
    @NotBlank(message = "游戏名称不能为空")
    @ApiModelProperty(name = "name", value = "游戏名称", required = true, dataType = "String")
    private String name;

    /**
     * 故事背景
     */
    @NotBlank(message = "故事背景不能为空")
    @ApiModelProperty(name = "storyBackground", value = "故事背景", required = true, dataType = "String")
    private String storyBackground;

    /**
     * 操作类型
     */
    @NotBlank(message = "操作类型不能为空")
    @ApiModelProperty(name = "playType", value = "操作类型", required = true, dataType = "Integer")
    private Integer playType;
    /**
     * 剧本类型
     */
    @NotBlank(message = "storeType")
    @ApiModelProperty(name = "code", value = "剧本类型", required = true, dataType = "Integer")
    private Integer storeType;
    /**
     * 状态
     */
    @NotBlank(message = "state")
    @ApiModelProperty(name = "state", value = "剧本状态", required = true, dataType = "Integer")
    private Integer state;
    /**
     * 创建者id
     */
    @NotBlank(message = "createdBy")
    @ApiModelProperty(name = "createdBy", value = "用户账号", required = true, dataType = "String")
    private String createdBy;
}
