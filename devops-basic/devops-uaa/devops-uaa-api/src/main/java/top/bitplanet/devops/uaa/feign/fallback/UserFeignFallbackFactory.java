package top.bitplanet.devops.uaa.feign.fallback;

import top.bitplanet.devops.support.core.framework.page.TYPageQuery;
import top.bitplanet.devops.support.core.framework.page.TYPageResp;
import top.bitplanet.devops.support.core.helper.R;
import top.bitplanet.devops.uaa.dto.UserResp;
import top.bitplanet.devops.uaa.dto.query.UserQuery;
import top.bitplanet.devops.uaa.feign.UserFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  UserFeign 熔断工厂
 * </p>
 *
 * @author Le
 * @since 2022/1/10
 */
@Slf4j
@Component
public class UserFeignFallbackFactory implements FallbackFactory<UserFeign> {
    @Override
    public UserFeign create(Throwable cause) {
        log.error("熔断错误打印",cause);
        return new UserFeign() {
            @Override
            public R<String> add(UserQuery query) {
                log.error("cookieValue:" + query);
                log.error(cause.getMessage());
                return null;
            }

            @Override
            public R<Long> delete(Long id) {
                log.error(cause.getMessage());
                return null;
            }

            @Override
            public R<String> update(Long id, UserQuery query) {
                log.error(cause.getMessage());
                return null;
            }

            @Override
            public R<UserResp> detail(Long id) {
                log.error(cause.getMessage());
                return null;
            }

            @Override
            public R<TYPageResp<UserResp>> page(TYPageQuery<UserQuery> tyPageQuery) {
                log.error(cause.getMessage());
                return null;
            }
        };
    }
}
