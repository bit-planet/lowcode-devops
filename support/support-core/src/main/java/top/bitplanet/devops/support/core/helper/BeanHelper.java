package top.bitplanet.devops.support.core.helper;

import cn.hutool.core.bean.BeanUtil;

import java.util.Collection;
import java.util.List;

/**
 *  bean 对象处理工具类
 * <p>
 *
 * </p>
 *
 * @author Le
 * @version 1.0.0
 * @since 2021/12/13 11:04
 */
public class BeanHelper {


    /**
     * 复制Bean对象属性<br>
     * @param src              源Bean对象
     * @param target           目标Bean对象
     * @param ignoreProperties 不拷贝的的属性列表
     */
    public static void copy(Object src,Object target,String... ignoreProperties) {
        BeanUtil.copyProperties(src,target,ignoreProperties);
    }


    /**
     * 复制Bean对象属性 根据class创建对象<br>
     * @param src              源Bean对象
     * @param target           目标class
     * @param ignoreProperties 不拷贝的的属性列表
     */
    public static <T> T copy(Object src,Class<T> target,String... ignoreProperties) {
        return BeanUtil.copyProperties(src,target,ignoreProperties);
    }


    public static <T> List<T> copyList(Collection collection,Class<T> clazz) {
        return BeanUtil.copyToList(collection,clazz);
    }
}
