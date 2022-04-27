package top.bitplanet.devops.lowcode.help;





import javax.naming.Reference;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * <p>
 *
 * </p>
 *
 * @author Administrator
 * @date 2021/12/12 0012 21:29
 * @since 1.0
 */
public class RMITest {

    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.createRegistry(1099);
        Reference reference = new Reference("top.bitplanet.devops.lowcode.help.RemoteCode","top.bitplanet.devops.lowcode.help.RemoteCode","http://127.0.0.1:8081");
//        ReferenceWrapper referenceWrapper = new ReferenceWrapper(reference);
//
//        registry.bind("remote",referenceWrapper);
    }
}
