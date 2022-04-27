package top.bitplanet.devops.lowcode.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.bitplanet.devops.lowcode.entity.DatasourceInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 数据源信息 Mapper 接口
 * </p>
 *
 * @author Le
 * @since 2021-11-18
 */
@Mapper
public interface DatasourceInfoMapper extends BaseMapper<DatasourceInfo> {

}
