//package top.bitplanet.devops.uaa.config;
//
//import top.bitplanet.devops.uaa.mapper.UserMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;
//import org.springframework.security.oauth2.provider.OAuth2Authentication;
//import org.springframework.security.oauth2.provider.token.TokenEnhancer;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * JWT内容增强器
// * Created by Le on 2020/6/19.
// */
//@Slf4j
//@Component
//public class JwtTokenEnhancer implements TokenEnhancer {
//
//    @Resource
//    private UserMapper userMapper;
//
//    @Override
//    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
//        log.debug("oath2=========>token增强器");
////        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
//        Map<String, Object> info = new HashMap<>();
//        // 放权限内容
//        info.put("test","----测试内容------");
//        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
//        return accessToken;
//    }
//}
