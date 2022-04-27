package top.bitplanet.devops.lowcode.wiki.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import top.bitplanet.devops.support.mybatisplus.base.BaseEntity;
import java.io.Serializable;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import top.bitplanet.devops.lowcode.wiki.dto.WikiPageResp;
import top.bitplanet.devops.lowcode.wiki.dto.query.WikiPageQuery;

/**
 * <p>
 * wiki知识库page页
 * </p>
 *
 * @author Le
 * @since 2022-02-22
 */
@Data
@TableName("lcp_wiki_page")
@Api(value = "WikiPage对象", tags = "wiki知识库page页")
public class WikiPage extends BaseEntity<WikiPageQuery,WikiPageResp> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("markdown草稿")
    private String markdownDraft;

    @ApiModelProperty("markdown文本")
    private String markdown;

    @ApiModelProperty("展示用的html")
    private String html;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("类型")
    private Integer type;


}
