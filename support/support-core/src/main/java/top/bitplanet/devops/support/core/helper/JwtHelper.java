package top.bitplanet.devops.support.core.helper;

import cn.hutool.core.exceptions.ValidateException;
import cn.hutool.crypto.KeyUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTHeader;
import cn.hutool.jwt.JWTValidator;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import top.bitplanet.devops.support.core.exception.TokenValidateException;

import java.io.InputStream;
import java.security.KeyPair;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  jwt 生成 ，验证 帮助类
 * </p>
 *
 * @author Le
 * @version 1.0.0
 * @date 2022/01/20
 */
public class JwtHelper {

    private static String id = "rs256";
    private static JWTSigner signer ;

    static {
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jwt.jks");
        KeyPair keyPair = getKeyPair(is);
        signer = JWTSignerUtil.createSigner(id,keyPair);
    }

    /**
     * 读取jks证书获取 keypair对象
     * @param is
     * @return
     */
    public static KeyPair getKeyPair(InputStream is) {
        KeyPair jks = KeyUtil.getKeyPair("jks", is, "hellxzTest".toCharArray(), "hellxz-jwt");
        return jks;
    }

    /**
     * 创建一个经过rsa 加密的token，并设置过期时间
     * @param expiresSeconds 过期时间，单位：s
     * @return
     */
    public static String createWithRsa256(long expiresSeconds) {
        long now = System.currentTimeMillis();
        String token = JWT.create()
                .setIssuedAt(new Date(now))
                .setNotBefore(new Date(now))
                .setExpiresAt(new Date(now + expiresSeconds * 1000))
                .setPayload("sub", 1234567890)
                .setPayload("name", "looly")
                .setPayload("admin", true)
                .setSigner(signer)
                .sign();
        return token;
    }

    /**
     * 解密token，并讲荷载转换成map对象，不做有效性验证
     * @param token
     * @return
     */
    public static Map<String,Object> decodeWithRsa256(String token) {
        JWT jwt = JWT.of(token);
        // JWT
        jwt.getHeader(JWTHeader.TYPE);
        // HS256
        jwt.getHeader(JWTHeader.ALGORITHM);
        JSONObject payloads = jwt.getPayloads();
        Map<String,Object> map = new HashMap();
        payloads.forEach((key, value) -> map.put(key,value));
        return map;
    }

    /**
     * ！！！此方法只会对签名有效性做验证，并不会验证时间的有效性
     * @param token
     * @return
     */
    @Deprecated
    public static boolean verify(String token) {
        boolean verify = JWT.of(token).setSigner(signer).verify();
        return verify;
    }

    /**
     * 验证token，签名和有效性和时间
     * @param token
     * @throws TokenValidateException
     */
    public static void validate(String token) throws TokenValidateException {
        try {
            JWTValidator.of(token).validateAlgorithm(signer).validateDate(new Date());
        } catch (ValidateException e) {
            throw new TokenValidateException(e.getMessage());
        }
    }


}
