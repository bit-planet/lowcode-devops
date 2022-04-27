package top.bitplanet.devops.uaa.feign;

import top.bitplanet.devops.support.core.framework.page.TYPageQuery;
import top.bitplanet.devops.support.core.framework.page.TYPageResp;
import top.bitplanet.devops.support.core.helper.R;
import top.bitplanet.devops.uaa.dto.UserResp;
import top.bitplanet.devops.uaa.dto.query.UserQuery;
import org.springframework.cloud.openfeign.FeignClient;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户登录信息 feign 对象
 * </p>
 *
 * @author Le
 * @since 2022-02-09
 */
@FeignClient(contextId = "uaaUserFeign",name = "uaa",path = "/user")
public interface UserFeign {

    /**
    * 新增 用户登录信息
    * @param query
    * @return
    */
    @PostMapping("")
    public R<String> add(@RequestBody @Valid UserQuery query);

    /**
     * 根据 id 删除 用户登录信息
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public R<Long> delete(@PathVariable("id") Long id);

    /**
     * 根据id 修改 用户登录信息
     * @param id
     * @param query
     * @return
     */
    @PutMapping("/{id}")
    public R<String> update(@PathVariable("id") Long id, @RequestBody UserQuery query);

    /**
     * 根据id 查询用户登录信息详情
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<UserResp> detail(@PathVariable("id") Long id);

    /**
     * 通用分页查询
     * @param tyPageQuery
     * @return
     */
    @GetMapping("/page")
    public R<TYPageResp<UserResp>> page(TYPageQuery<UserQuery> tyPageQuery);

}