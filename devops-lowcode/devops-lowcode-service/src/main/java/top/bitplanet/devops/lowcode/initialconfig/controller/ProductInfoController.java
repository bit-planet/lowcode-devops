package top.bitplanet.devops.lowcode.initialconfig.controller;

import javax.annotation.Resource;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import top.bitplanet.devops.support.core.helper.R;
import top.bitplanet.devops.support.core.framework.page.TYPageQuery;
import top.bitplanet.devops.support.core.framework.page.TYPageResp;
import top.bitplanet.devops.lowcode.initialconfig.service.IProductInfoService;
import top.bitplanet.devops.lowcode.initialconfig.dto.ProductInfoResp;
import top.bitplanet.devops.lowcode.initialconfig.dto.query.ProductInfoQuery;
/**
 * <p>
 * 初始化配置-产品配置信息 前端控制器
 * </p>
 *
 * @author Le
 * @date 2022-02-07
 */
@RestController
@RequestMapping("/initialconfig/productInfo")
public class ProductInfoController {
    @Resource
    private IProductInfoService productInfoService;

    /**
     * 新增 初始化配置-产品配置信息
     * @param query
     * @return
     */
    @PostMapping("")
    public R<String> add(@RequestBody @Valid ProductInfoQuery query) {
        boolean result = productInfoService.saveByQuery(query);
        if (!result) {
            return R.fail("保存失败！");
        }
        return R.success("保持成功");
    }

    /**
     * 根据 id 删除 初始化配置-产品配置信息
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public R<Long> delete(@PathVariable("id") Long id) {
        boolean result = productInfoService.removeById(id);
        if (!result) {
            return R.fail("删除失败！");
        }
        return R.success(id);
    }

    /**
     * 根据id 修改 初始化配置-产品配置信息
     * @param id
     * @param query
     * @return
     */
    @PutMapping("/{id}")
    public R<String> update(@PathVariable("id") Long id, @RequestBody @Valid ProductInfoQuery query) {
        query.setId(id);
        boolean result = productInfoService.updateByQuery(query);
        if (!result) {
            return R.fail("修改失败！");
        }
        return R.success("修改成功");
    }

    /**
     * 根据id 查询 初始化配置-产品配置信息 详情
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<ProductInfoResp> detail(@PathVariable("id") Long id) {
        ProductInfoResp resp = productInfoService.getRespById(id);
        return R.success(resp);
    }

    /**
     * 通用分页查询
     * @param tyPageQuery
     * @return
     */
    @GetMapping("/page")
    public R<TYPageResp<ProductInfoResp>> page(TYPageQuery<ProductInfoQuery> tyPageQuery) {
        TYPageResp<ProductInfoResp> page = productInfoService.pageResp(tyPageQuery);
        return R.success(page);
    }


}