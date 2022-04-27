package top.bitplanet.devops.support.webmvc;

import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * <p>
 *  默认微服务配置
 * </p>
 *
 * @author Le
 * @since 2022/1/10
 */
public class TyDefaultMicroServiceConfiguration {

    /**
     * sentinel 整合 restTemplate
     *
     * @return
     */
    @Bean
    @SentinelRestTemplate(blockHandler = "handleException", blockHandlerClass = ExceptionUtil.class)
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }



}
