package top.bitplanet.devops.support.core.helper;

/**
 * <p>
 *  首字母大小写帮助类
 * </p>
 *
 * @author Le
 * @version 1.0.0
 * @since 2021/12/24 16:10
 */
public class InitialHelper {

    public static String toUpperCase(String name) {
        if (StringHelper.isEmpty(name) || name.length() < 1) {
            return null;
        }
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        return  name;
    }

    public static String toLowerCase(String name) {
        if (StringHelper.isEmpty(name) || name.length() < 1) {
            return null;
        }
        name = name.substring(0, 1).toLowerCase() + name.substring(1);
        return  name;
    }
}
