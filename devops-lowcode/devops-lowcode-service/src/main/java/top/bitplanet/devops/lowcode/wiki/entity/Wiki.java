package top.bitplanet.devops.lowcode.wiki.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import top.bitplanet.devops.lowcode.wiki.po.Tree;
import top.bitplanet.devops.support.mybatisplus.base.BaseEntity;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import top.bitplanet.devops.lowcode.wiki.dto.WikiResp;
import top.bitplanet.devops.lowcode.wiki.dto.query.WikiQuery;

/**
 * <p>
 * wiki知识库
 * </p>
 *
 * @author Le
 * @since 2022-02-21
 */
@Data
@TableName(value = "lcp_wiki",autoResultMap = true)
@Api(value = "Wiki对象", tags = "wiki知识库")
public class Wiki extends BaseEntity<WikiQuery,WikiResp> {

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

    @ApiModelProperty("菜单树")
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private List<Tree> treeJson;

}
