package top.bitplanet.devops.support.core.helper;

import cn.hutool.json.JSONUtil;

/**
 * <p>
 *
 * </p>
 *
 * @author Le
 * @version 1.0.0
 * @since 2021/12/14 16:44
 */
public class JsonHelper {

    public static String toJson(Object obj) {
       return JSONUtil.toJsonStr(obj);
    }

    public static byte[] writeBytes(Object obj) {
        return JSONUtil.toJsonStr(obj).getBytes();
    }
}
