//package top.bitplanet.devops.uaa.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.TokenEnhancer;
//import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
//import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
//
//import javax.annotation.Resource;
//import java.util.ArrayList;
//import java.util.List;
//
//@Configuration
//@EnableAuthorizationServer
//public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
//
//    private static final String DEMO_RESOURCE_ID = "bitplanet";
//
//    @Autowired
//    AuthenticationManager authenticationManager;
//
//    @Autowired
//    JwtAccessTokenConverter JwtAccessTokenConverter;
//    @Resource
//    UserDetailsService userDetailsService;
//
//    @Autowired
//    JwtTokenEnhancer jwtTokenEnhancer;
//
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        //配置两个客户端,一个用于password认证一个用于client认证
//
//        String secret = new BCryptPasswordEncoder().encode("cjzh-A&78a44-33l.");////对密码进行加密
//        clients.inMemory()
//                .withClient("client")
//                .resourceIds(DEMO_RESOURCE_ID)
//                .authorizedGrantTypes("password")
//                .scopes("web")
//                .secret(secret)
//                .accessTokenValiditySeconds(60000)
//                .refreshTokenValiditySeconds(120000);;
//    }
//
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//
//        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
//        List<TokenEnhancer> delegates = new ArrayList<>();
//        delegates.add(jwtTokenEnhancer);
//        delegates.add(JwtAccessTokenConverter);
//        enhancerChain.setTokenEnhancers(delegates); //配置JWT的内容增强器
//
//        endpoints.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
//                .tokenStore(new JwtTokenStore(JwtAccessTokenConverter))
//                .userDetailsService(userDetailsService)
//                .authenticationManager(authenticationManager)
//                .tokenEnhancer(enhancerChain);
//    }
//
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
//        //允许表单认证
//        oauthServer.allowFormAuthenticationForClients()
//                .tokenKeyAccess("permitAll()")
//                .checkTokenAccess("isAuthenticated()");
//    }
//
//
//
//    /**
//     * token生成处理：指定签名
//     */
//    @Primary
//    @Bean
//    public JwtAccessTokenConverter jwtAccessTokenConverter(){
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        KeyStoreKeyFactory storeKeyFactory = new KeyStoreKeyFactory(
//                new ClassPathResource("jwt.jks"), "hellxzTest".toCharArray());
//        converter.setKeyPair(storeKeyFactory.getKeyPair("hellxz-jwt"));
//        return converter;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//}
//
