package top.bitplanet.devops.lowcode;

import top.bitplanet.devops.support.swagger.TyEnableOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableFeignClients("top.bitplanet.devops.uaa.feign")
@TyEnableOpenApi(enable = true)
@EnableAsync
public class LowcodeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LowcodeServiceApplication.class,args);
    }

}
