package top.bitplanet.devops.lowcode.initialconfig.service.impl;

import top.bitplanet.devops.lowcode.initialconfig.entity.ModuleInfo;
import top.bitplanet.devops.lowcode.initialconfig.dto.ModuleInfoResp;
import top.bitplanet.devops.lowcode.initialconfig.dto.query.ModuleInfoQuery;
import top.bitplanet.devops.lowcode.initialconfig.mapper.ModuleInfoMapper;
import top.bitplanet.devops.lowcode.initialconfig.service.IModuleInfoService;
import org.springframework.stereotype.Service;
import top.bitplanet.devops.support.mybatisplus.base.TYBaseServiceImpl;

/**
 * <p>
 * 初始化配置 - 模块信息 服务实现类
 * </p>
 *
 * @author Le
 * @since 2022-02-07
 */
@Service
public class ModuleInfoServiceImpl extends TYBaseServiceImpl<ModuleInfoMapper, ModuleInfo, ModuleInfoQuery, ModuleInfoResp> implements IModuleInfoService {

}
