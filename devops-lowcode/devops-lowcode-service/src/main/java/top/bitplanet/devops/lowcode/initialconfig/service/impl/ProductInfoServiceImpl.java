package top.bitplanet.devops.lowcode.initialconfig.service.impl;

import top.bitplanet.devops.lowcode.initialconfig.entity.ProductInfo;
import top.bitplanet.devops.lowcode.initialconfig.dto.ProductInfoResp;
import top.bitplanet.devops.lowcode.initialconfig.dto.query.ProductInfoQuery;
import top.bitplanet.devops.lowcode.initialconfig.mapper.ProductInfoMapper;
import top.bitplanet.devops.lowcode.initialconfig.service.IProductInfoService;
import org.springframework.stereotype.Service;
import top.bitplanet.devops.support.mybatisplus.base.TYBaseServiceImpl;

/**
 * <p>
 * 初始化配置-产品配置信息 服务实现类
 * </p>
 *
 * @author Le
 * @since 2022-02-07
 */
@Service
public class ProductInfoServiceImpl extends TYBaseServiceImpl<ProductInfoMapper, ProductInfo, ProductInfoQuery, ProductInfoResp> implements IProductInfoService {

}
