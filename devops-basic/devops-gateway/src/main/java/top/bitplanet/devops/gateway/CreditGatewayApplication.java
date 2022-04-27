package top.bitplanet.devops.gateway;

import top.bitplanet.devops.support.swagger.TyEnableOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.discovery.GatewayDiscoveryClientAutoConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 *  exclude 排除springboot 默认的自动装配
 *  <p>
 *  GatewayDiscoveryClientAutoConfiguration : 重写注册中心自动路由配置类
 *  </p>
 *  @see top.bitplanet.devops.gateway.config.discovery.GatewayDiscoveryClientAutoConfiguration
 *
 */
@SpringBootApplication(exclude = {GatewayDiscoveryClientAutoConfiguration.class})
@PropertySources({@PropertySource("classpath:/security/ignoreUrl.properties")})
@TyEnableOpenApi( enable = true, description = "======测试自定义注解gateway=====", version = "1.0.0-")
public class CreditGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(CreditGatewayApplication.class, args);
    }

}
