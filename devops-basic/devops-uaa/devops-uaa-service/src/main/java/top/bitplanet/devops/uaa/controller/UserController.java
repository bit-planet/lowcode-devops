package top.bitplanet.devops.uaa.controller;

import javax.annotation.Resource;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import top.bitplanet.devops.support.core.helper.R;
import top.bitplanet.devops.support.core.framework.page.TYPageQuery;
import top.bitplanet.devops.support.core.framework.page.TYPageResp;
import top.bitplanet.devops.uaa.service.IUserService;
import top.bitplanet.devops.uaa.dto.UserResp;
import top.bitplanet.devops.uaa.dto.query.UserQuery;
/**
 * <p>
 * 用户登录信息 前端控制器
 * </p>
 *
 * @author Le
 * @date 2022-02-09
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;

    /**
     * 新增 用户登录信息
     * @param query
     * @return
     */
    @PostMapping("")
    public R<String> add(@RequestBody @Valid UserQuery query) {
        boolean result = userService.saveByQuery(query);
        if (!result) {
            return R.fail("保存失败！");
        }
        return R.success("保持成功");
    }

    /**
     * 根据 id 删除 用户登录信息
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public R<Long> delete(@PathVariable("id") Long id) {
        boolean result = userService.removeById(id);
        if (!result) {
            return R.fail("删除失败！");
        }
        return R.success(id);
    }

    /**
     * 根据id 修改 用户登录信息
     * @param id
     * @param query
     * @return
     */
    @PutMapping("/{id}")
    public R<String> update(@PathVariable("id") Long id, @RequestBody @Valid UserQuery query) {
        query.setId(id);
        boolean result = userService.updateByQuery(query);
        if (!result) {
            return R.fail("修改失败！");
        }
        return R.success("修改成功");
    }

    /**
     * 根据id 查询 用户登录信息 详情
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<UserResp> detail(@PathVariable("id") Long id) {
        UserResp resp = userService.getRespById(id);
        return R.success(resp);
    }

    /**
     * 通用分页查询
     * @param tyPageQuery
     * @return
     */
    @GetMapping("/page")
    public R<TYPageResp<UserResp>> page(TYPageQuery<UserQuery> tyPageQuery) {
        TYPageResp<UserResp> page = userService.pageResp(tyPageQuery);
        return R.success(page);
    }


}