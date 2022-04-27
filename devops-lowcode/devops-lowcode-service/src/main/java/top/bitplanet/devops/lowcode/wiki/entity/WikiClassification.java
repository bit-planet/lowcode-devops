package top.bitplanet.devops.lowcode.wiki.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import top.bitplanet.devops.support.mybatisplus.base.BaseEntity;
import java.io.Serializable;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import top.bitplanet.devops.lowcode.wiki.dto.WikiClassificationResp;
import top.bitplanet.devops.lowcode.wiki.dto.query.WikiClassificationQuery;

/**
 * <p>
 * wiki分类
 * </p>
 *
 * @author Le
 * @since 2022-03-07
 */
@Data
@TableName("lcp_wiki_classification")
@Api(value = "WikiClassification对象", tags = "wiki分类")
public class WikiClassification extends BaseEntity<WikiClassificationQuery,WikiClassificationResp> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("父ID")
    private Long parentId;

    @ApiModelProperty("分类名")
    private String classificationName;

    @ApiModelProperty("分类下wiki总数")
    private Integer wikiSum;

    @ApiModelProperty("分类介绍")
    private String introduction;


}
