package top.bitplanet.devops.lowcode.help;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.spi.ObjectFactory;
import java.util.Hashtable;

/**
 * <p>
 *
 * </p>
 *
 * @author Administrator
 * @date 2021/12/12 0012 21:35
 * @since 1.0
 */
public class RemoteCode implements ObjectFactory {
    static {
        System.out.println("攻击者代码");

    }


    @Override
    public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) throws Exception {
        System.out.println("调用");

        return "success";
    }
}
