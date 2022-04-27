package top.bitplanet.devops.lowcode.initialconfig.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import top.bitplanet.devops.support.mybatisplus.base.BaseEntity;
import java.io.Serializable;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import top.bitplanet.devops.lowcode.initialconfig.dto.ProductInfoResp;
import top.bitplanet.devops.lowcode.initialconfig.dto.query.ProductInfoQuery;

/**
 * <p>
 * 初始化配置-产品配置信息
 * </p>
 *
 * @author Le
 * @since 2022-02-07
 */
@Data
@TableName("lcp_product_info")
@Api(value = "ProductInfo对象", tags = "初始化配置-产品配置信息")
public class ProductInfo extends BaseEntity<ProductInfoQuery,ProductInfoResp> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("公司/组织")
    private String companyName;

    @ApiModelProperty("产品/工程组")
    private String productName;

    @ApiModelProperty("初始版本号")
    private String version;

    @ApiModelProperty("产品描述")
    private String description;


}
