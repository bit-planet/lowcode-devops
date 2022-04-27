package top.bitplanet.devops.lowcode.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import top.bitplanet.devops.lowcode.entity.DatasourceInfo;
import top.bitplanet.devops.lowcode.mapper.DatasourceInfoMapper;
import top.bitplanet.devops.lowcode.service.IDatasourceInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.bitplanet.devops.support.core.helper.DBHelper;
import top.bitplanet.devops.lowcode.dto.query.DataSourceInfoQuery;
import org.springframework.stereotype.Service;

import java.sql.Connection;

/**
 * <p>
 * 数据源信息 服务实现类
 * </p>
 *
 * @author Le
 * @since 2021-11-18
 */
@Service
public class DatasourceInfoServiceImpl extends ServiceImpl<DatasourceInfoMapper, DatasourceInfo> implements IDatasourceInfoService {

    @Override
    @SentinelResource(value = "sayHello")
    public Connection getConnectionById(long datasourceInfoId) {
        DatasourceInfo info = getById(datasourceInfoId);
        DataSourceInfoQuery dataSourceInfoQuery = info.convertToQuery();
        Connection connection = DBHelper.getConnection(dataSourceInfoQuery.getJdbcUrl(),
                dataSourceInfoQuery.getUsername(), dataSourceInfoQuery.getPassword());
        return connection;
    }
}
