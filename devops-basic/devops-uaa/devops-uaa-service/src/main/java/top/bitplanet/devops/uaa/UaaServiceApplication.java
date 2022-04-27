package top.bitplanet.devops.uaa;

import top.bitplanet.devops.support.swagger.TyEnableOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@TyEnableOpenApi(enable = true)
public class UaaServiceApplication {


    public static void main(String[] args) {
        SpringApplication.run(UaaServiceApplication.class,args);
    }

}
