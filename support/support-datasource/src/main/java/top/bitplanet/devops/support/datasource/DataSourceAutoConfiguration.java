package top.bitplanet.devops.support.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@AutoConfigureBefore(DruidDataSourceAutoConfigure.class)
public class DataSourceAutoConfiguration {

    @Resource
    private DataSourceProperties dataSourceProperties;

    @Bean("myDataSource")
    public DataSource getDataSource() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        // @TODO  为兼容老版本，避免nacos配置混淆，暂时写死
        druidDataSource.setDriverClassName("org.postgresql.Driver");
        druidDataSource.setUrl(dataSourceProperties.getUrl());
        druidDataSource.setUsername(dataSourceProperties.getUsername());
        druidDataSource.setPassword(dataSourceProperties.getPassword());
        druidDataSource.setFilters(dataSourceProperties.getFilters());
        druidDataSource.setConnectionProperties("config.decrypt=true;config.decrypt.key=" + dataSourceProperties.getPublickey());
        return druidDataSource;
    }

}
