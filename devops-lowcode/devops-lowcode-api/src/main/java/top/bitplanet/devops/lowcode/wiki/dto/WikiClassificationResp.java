package top.bitplanet.devops.lowcode.wiki.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

/**
 * <p>
 * wiki分类 通用 Response 对象
 * </p>
 *
 * @author Le
 * @since 2022-03-07
 */
@Data
@Api(value = "WikiClassification Response对象", tags = "wiki分类")
public class WikiClassificationResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("父ID")
    private Long parentId;

    @ApiModelProperty("分类名")
    private String classificationName;

    @ApiModelProperty("分类下wiki总数")
    private Integer wikiSum;

    @ApiModelProperty("分类介绍")
    private String introduction;

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("创建人ID")
    private Long createUserId;

    @ApiModelProperty("修改人ID")
    private Long updateUserId;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("删除标志")
    private Boolean deleteFlag;


}
