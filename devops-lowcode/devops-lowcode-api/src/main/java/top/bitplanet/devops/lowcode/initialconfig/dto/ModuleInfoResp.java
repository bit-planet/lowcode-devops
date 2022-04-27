package top.bitplanet.devops.lowcode.initialconfig.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 初始化配置 - 模块信息 通用 Response 对象
 * </p>
 *
 * @author Le
 * @since 2022-02-07
 */
@Data
@Api(value = "ModuleInfo Response对象", tags = "初始化配置 - 模块信息")
public class ModuleInfoResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("产品ID")
    private Long productId;

    @ApiModelProperty("模块名")
    private String moduleName;

    @ApiModelProperty("模块描述")
    private String description;

    @ApiModelProperty("后端Git目录")
    private String backGitDir;

    @ApiModelProperty("后端模块目录")
    private String backModuleDir;

    @ApiModelProperty("前端Git目录")
    private String frontGitDir;

    @ApiModelProperty("前端模块目录")
    private String frontModuleDir;

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
