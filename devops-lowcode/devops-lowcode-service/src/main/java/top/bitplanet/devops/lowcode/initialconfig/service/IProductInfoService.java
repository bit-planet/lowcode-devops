package top.bitplanet.devops.lowcode.initialconfig.service;

import top.bitplanet.devops.lowcode.initialconfig.entity.ProductInfo;
import top.bitplanet.devops.lowcode.initialconfig.dto.ProductInfoResp;
import top.bitplanet.devops.lowcode.initialconfig.dto.query.ProductInfoQuery;
import top.bitplanet.devops.support.mybatisplus.base.TYBaseService;

/**
 * <p>
 * 初始化配置-产品配置信息 服务类
 * </p>
 *
 * @author Le
 * @since 2022-02-07
 */
public interface IProductInfoService extends TYBaseService<ProductInfo, ProductInfoQuery, ProductInfoResp> {

}
