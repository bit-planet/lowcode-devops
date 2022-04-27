package top.bitplanet.devops.support.swagger;


import top.bitplanet.devops.support.core.helper.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;


@Configuration
@ComponentScan("top.bitplanet.devops.support.swagger")
public class BitPlanetSwagger3Config {

    @Autowired(required = false)
    private TySwaggerApiInfo swaggerApiInfo = new TySwaggerApiInfo();
    @Value("${maven.project.version}")
    private String version;
    @Value("${maven.project.description}")
    private String description;

    @Bean
    public Docket createRestApi() {

        return new Docket(DocumentationType.OAS_30) // 注意此处改动，需要将SWAGGER_2改成OAS_30
                //是否启动
                .enable(swaggerApiInfo.getEnable())
                //头部信息
                .apiInfo(apiInfo())
                .select()
                /**
                 * RequestHandlerSelectors,配置要扫描接口的方式
                 * basePackage指定要扫描的包
                 * any()扫描所有，项目中的所有接口都会被扫描到
                 * none()不扫描
                 * withClassAnnotation()扫描类上的注解
                 * withMethodAnnotation()扫描方法上的注解
                 */
                .apis(RequestHandlerSelectors.any())
                //过滤某个路径
                .paths(PathSelectors.any())
                .build()
                //协议
                .protocols(newHashSet("https", "http"));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(swaggerApiInfo.getTitle())
                .description(StringHelper.isEmpty(swaggerApiInfo.getDescription())?description:swaggerApiInfo.getDescription())
                .contact(new Contact(swaggerApiInfo.getContactName(), swaggerApiInfo.getContactUrl(), swaggerApiInfo.getContactEmail()))
                .version(StringHelper.isEmpty(swaggerApiInfo.getVersion())?version:swaggerApiInfo.getVersion())
                .build();
    }

    @SafeVarargs
    private final <T> Set<T> newHashSet(T... ts) {
        if (ts.length > 0) {
            return new LinkedHashSet<>(Arrays.asList(ts));
        }
        return null;
    }

}