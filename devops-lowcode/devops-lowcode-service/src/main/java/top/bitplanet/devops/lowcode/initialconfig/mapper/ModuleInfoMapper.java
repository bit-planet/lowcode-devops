package top.bitplanet.devops.lowcode.initialconfig.mapper;

import top.bitplanet.devops.lowcode.initialconfig.entity.ModuleInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 初始化配置 - 模块信息 Mapper 接口
 * </p>
 *
 * @author Le
 * @since 2022-02-07
 */
@Mapper
public interface ModuleInfoMapper extends BaseMapper<ModuleInfo> {

}
