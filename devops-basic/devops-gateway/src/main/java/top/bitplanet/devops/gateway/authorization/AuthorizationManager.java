package top.bitplanet.devops.gateway.authorization;

import top.bitplanet.devops.support.core.exception.TokenValidateException;
import top.bitplanet.devops.support.core.helper.JwtHelper;
import top.bitplanet.devops.support.core.helper.StringHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.List;

/**
 * 鉴权管理器，用于判断是否有资源的访问权限
 * Created by macro on 2020/6/19.
 */
@Component
@Slf4j
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {
//    @Autowired
//    private RedisTemplate<String,Object> redisTemplate;

    private RSAPublicKey publicKey;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        // TODO 调试放行。不做token校验
        if (1 == 1) {
            return Mono.just(new AuthorizationDecision(true));
        }
        ServerWebExchange exchange = authorizationContext.getExchange();
        // 验证token合法性
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (StringHelper.isEmpty(token)) {
            // return chain.filter(exchange);
            return Mono.just(new AuthorizationDecision(false));
        }
        try {
            JwtHelper.validate(token);
        } catch (TokenValidateException e) {
            log.error("token解析异常：",e);
            return Mono.just(new AuthorizationDecision(false));
        }
        /**
         *  验证权限
         */
        URI uri = authorizationContext.getExchange().getRequest().getURI();
//        Object obj = redisTemplate.opsForHash().get(RedisConstant.RESOURCE_ROLES_MAP, uri.getPath());
        List<String> authorities = new ArrayList<>();
        authorities.add("/lowcode/rapid/test");
        Mono<AuthorizationDecision> authorizationDecision = Flux.just(uri.getPath())
                .any(authorities::contains)
                .map(AuthorizationDecision::new)
                .defaultIfEmpty(new AuthorizationDecision(false));
        return authorizationDecision;
    }


    @Override
    public Mono<Void> verify(Mono<Authentication> authentication, AuthorizationContext object) {
        return Mono.error(new AccessDeniedException("Access Denied"));
//        return check(authentication, object)
//                .filter(AuthorizationDecision::isGranted)
//                .switchIfEmpty(Mono.defer(() -> Mono.error(new AccessDeniedException("Access Denied"))))
//                .flatMap((decision) -> Mono.empty());

    }



    public RSAPublicKey getPublicKey() {
        if (publicKey == null) {
            KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "hellxzTest".toCharArray());
            KeyPair keyPair = keyStoreKeyFactory.getKeyPair("hellxz-jwt");
            publicKey =  (RSAPublicKey) keyPair.getPublic();
        }
        return publicKey;
    }

    public static void main(String[] args) {
        List<String> authorities = new ArrayList<>();
        authorities.add("http://localhost/ee");
        Boolean block = Flux.just("http://localhost/ee")
                .any(authorities::contains).block();
        System.out.println(block);
    }

}