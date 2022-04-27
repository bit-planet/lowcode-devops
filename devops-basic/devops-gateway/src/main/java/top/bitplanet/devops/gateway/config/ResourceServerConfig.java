package top.bitplanet.devops.gateway.config;

import top.bitplanet.devops.gateway.authorization.AuthorizationManager;
import top.bitplanet.devops.gateway.component.RestAuthenticationEntryPoint;
import top.bitplanet.devops.gateway.component.RestfulAccessDeniedHandler;
import top.bitplanet.devops.gateway.filter.TokenAuthFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * 资源服务器配置
 * Created by macro on 2020/6/19.
 */
@AllArgsConstructor
@Configuration
@EnableWebFluxSecurity
public class ResourceServerConfig {
    private final AuthorizationManager authorizationManager;
    private final IgnoreUrlsConfig ignoreUrlsConfig;
    private final RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;
//    private final IgnoreUrlsRemoveJwtFilter ignoreUrlsRemoveJwtFilter;
    private final TokenAuthFilter tokenAuthFilter;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        // 自定义过滤器
        //http.addFilterBefore(ignoreUrlsRemoveJwtFilter, SecurityWebFiltersOrder.AUTHENTICATION);
        http.addFilterAfter(tokenAuthFilter,SecurityWebFiltersOrder.AUTHENTICATION);
        // 白名单
        String[] urls = ignoreUrlsConfig.getUrls().toArray(new String[0]);
        http.authorizeExchange()
                //.pathMatchers("/oauth/**","/v3/**").permitAll()
                // 白名单配置
                .pathMatchers(urls).permitAll()
                .anyExchange().access(authorizationManager)//鉴权管理器配置
                .and()
                .exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)//处理拒绝
                .authenticationEntryPoint(restAuthenticationEntryPoint)//处理未授权
                .and()
                .csrf().disable().httpBasic();
        return http.build();
    }






}
