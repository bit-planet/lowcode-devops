package top.bitplanet.devops.support.core.helper;

import cn.hutool.core.util.StrUtil;

public class StringHelper {

    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isNotEmpty(CharSequence cs) {
        return !isEmpty(cs);
    }

    public static String toUnderlineCase(String camelCase) {
        return StrUtil.toUnderlineCase(camelCase);
    }
}
