package top.bitplanet.devops.support.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * <p>
 * 读取工程信息，需要在service工程重写 META-INF/mavenInfo.properties
 * </p>
 * encoding = "UTF-8" 解决properties读取中文乱码问题
 *
 * @author Le
 * @since 2022-01-09
 */
@Configuration
@Data
@ConfigurationProperties(prefix = "maven.project")
@PropertySource(value = "classpath:/META-INF/mavenInfo.properties",encoding = "UTF-8")
public class MavenProjectInfoProperties {

    private String version;

    private String groupId;

    private String artifactId;

    private String description;
}
