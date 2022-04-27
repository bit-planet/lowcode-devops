package top.bitplanet.devops.lowcode.initialconfig.mapper;

import top.bitplanet.devops.lowcode.initialconfig.entity.ProductInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 初始化配置-产品配置信息 Mapper 接口
 * </p>
 *
 * @author Le
 * @since 2022-02-07
 */
@Mapper
public interface ProductInfoMapper extends BaseMapper<ProductInfo> {

}
