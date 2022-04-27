package top.bitplanet.devops.lowcode.initialconfig.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import top.bitplanet.devops.support.mybatisplus.base.BaseEntity;
import java.io.Serializable;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import top.bitplanet.devops.lowcode.initialconfig.dto.ModuleInfoResp;
import top.bitplanet.devops.lowcode.initialconfig.dto.query.ModuleInfoQuery;

/**
 * <p>
 * 初始化配置 - 模块信息
 * </p>
 *
 * @author Le
 * @since 2022-02-07
 */
@Data
@TableName("lcp_module_info")
@Api(value = "ModuleInfo对象", tags = "初始化配置 - 模块信息")
public class ModuleInfo extends BaseEntity<ModuleInfoQuery,ModuleInfoResp> {

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


}
