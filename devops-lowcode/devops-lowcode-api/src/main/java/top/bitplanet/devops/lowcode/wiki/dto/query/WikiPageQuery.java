package top.bitplanet.devops.lowcode.wiki.dto.query;

import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * wiki知识库page页 通用Query对象
 * </p>
 *
 * @author Le
 * @since 2022-02-22
 */
@Data
@Api(value = "WikiPageQuery 对象", tags = "wiki知识库page页")
public class WikiPageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("markdown草稿")
    private String markdownDraft;

    @ApiModelProperty("markdown文本")
    private String markdown;

    @ApiModelProperty("展示用的html")
    private String html;

    @ApiModelProperty("状态 0:有新内容未发布  1:发布成功")
    private Integer status;

    @ApiModelProperty("类型")
    private Integer type;

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
