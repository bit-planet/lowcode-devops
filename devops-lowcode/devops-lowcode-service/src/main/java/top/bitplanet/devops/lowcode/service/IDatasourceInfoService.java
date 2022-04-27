package top.bitplanet.devops.lowcode.service;

import top.bitplanet.devops.lowcode.entity.DatasourceInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.sql.Connection;

/**
 * <p>
 * 数据源信息 服务类
 * </p>
 *
 * @author Le
 * @since 2021-11-18
 */
public interface IDatasourceInfoService extends IService<DatasourceInfo> {

    Connection getConnectionById(long datasourceInfoId);
}
