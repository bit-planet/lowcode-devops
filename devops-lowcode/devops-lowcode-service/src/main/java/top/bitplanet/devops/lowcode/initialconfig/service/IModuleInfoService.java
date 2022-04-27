package top.bitplanet.devops.lowcode.initialconfig.service;

import top.bitplanet.devops.lowcode.initialconfig.entity.ModuleInfo;
import top.bitplanet.devops.lowcode.initialconfig.dto.ModuleInfoResp;
import top.bitplanet.devops.lowcode.initialconfig.dto.query.ModuleInfoQuery;
import top.bitplanet.devops.support.mybatisplus.base.TYBaseService;

/**
 * <p>
 * 初始化配置 - 模块信息 服务类
 * </p>
 *
 * @author Le
 * @since 2022-02-07
 */
public interface IModuleInfoService extends TYBaseService<ModuleInfo, ModuleInfoQuery, ModuleInfoResp> {

}
