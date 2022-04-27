package top.bitplanet.devops.lowcode.wiki.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import top.bitplanet.devops.lowcode.wiki.po.Tree;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * wiki知识库 通用 Response 对象
 * </p>
 *
 * @author Le
 * @since 2022-02-21
 */
@Data
@Api(value = "Wiki Response对象", tags = "wiki知识库")
public class WikiResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("wiki分类ID")
    private String classificationId;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("简介")
    private String introduction;

    @ApiModelProperty("对谁可见")
    private Integer privacy;

    @ApiModelProperty("允许协同")
    private Boolean allowCoordination;

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

    @ApiModelProperty("菜单树")
    private List<Tree> treeJson;


}
