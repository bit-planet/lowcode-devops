package top.bitplanet.devops.lowcode.initialconfig.dto.query;

import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 初始化配置-产品配置信息 通用Query对象
 * </p>
 *
 * @author Le
 * @since 2022-02-07
 */
@Data
@Api(value = "ProductInfoQuery 对象", tags = "初始化配置-产品配置信息")
public class ProductInfoQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("公司/组织")
    private String companyName;

    @ApiModelProperty("产品/工程组")
    private String productName;

    @ApiModelProperty("初始版本号")
    private String version;

    @ApiModelProperty("产品描述")
    private String description;

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
