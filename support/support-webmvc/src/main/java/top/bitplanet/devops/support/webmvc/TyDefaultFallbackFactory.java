package top.bitplanet.devops.support.webmvc;

import top.bitplanet.devops.support.core.helper.R;
import feign.Target;
// import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.cloud.openfeign.FallbackFactory;

import java.lang.reflect.Method;

@Slf4j
public class TyDefaultFallbackFactory<T> implements FallbackFactory {
    private Target<T> target;
    public TyDefaultFallbackFactory(Target<T> target) {
        this.target = target;
    }

    @Override
    public T create(Throwable throwable) {
        Class<T> type = target.type();
        String name = target.name();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(type);
        enhancer.setUseCache(true);
        //MethodInterceptor
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                log.error("{}-{}：触发规则",type,name);
                return R.fail("被限流了");
            }
        });
        return (T)enhancer.create();
    }
}
