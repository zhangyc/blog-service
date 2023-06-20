package com.minzheng.blog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_screenplay")
public class Screenplay {
    @ApiModelProperty(name = "剧本名称",notes = "")
    private String name ;
    /** 创建人 */
    @ApiModelProperty(name = "创建人",notes = "")
    private String createdBy ;
    /** 创建时间 */
    @ApiModelProperty(name = "创建时间",notes = "")
    private Date createdTime ;
    /** 更新人 */
    @ApiModelProperty(name = "更新人",notes = "")
    private String updatedBy ;
    /** 更新时间 */
    @ApiModelProperty(name = "更新时间",notes = "")
    private Date updatedTime ;
    /** 摘要 */
    @ApiModelProperty(name = "摘要",notes = "")
    private String summary ;
    /** 故事背景 */
    @ApiModelProperty(name = "故事背景",notes = "")
    private String storyBackground ;
    /** 操作类性 */
    @ApiModelProperty(name = "操作类性",notes = "")
    private Integer playType ;
    /** 剧本类性 */
    @ApiModelProperty(name = "剧本类性",notes = "")
    private Integer type ;
    /** 是否被删除 */
    @ApiModelProperty(name = "是否被删除",notes = "")
    private Integer isDelete ;
    /** 剧本状态（上架、下架、草稿） */
    @ApiModelProperty(name = "剧本状态（上架、下架、草稿）",notes = "")
    private Integer state ;
    /** 剧本id */
    @ApiModelProperty(name = "剧本id",notes = "")
    private Long id ;
}
