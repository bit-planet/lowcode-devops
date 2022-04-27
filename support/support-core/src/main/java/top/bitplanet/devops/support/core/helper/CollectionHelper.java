package top.bitplanet.devops.support.core.helper;

import cn.hutool.core.collection.CollectionUtil;

import java.util.Collection;

/**
 * <p>
 *
 * </p>
 *
 * @author Le
 * @version 1.0.0
 * @since 2021/12/13 11:04
 */
public class CollectionHelper {


    /**
     * 集合是否为空
     *
     * @param collection 集合
     * @return 是否为空
     */
    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 集合是否 不为空
     *
     * @param collection 集合
     * @return 是否为空
     */
    public static boolean isNotEmpty(Collection collection) {
        return !isEmpty(collection);
    }


}
