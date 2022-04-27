package top.bitplanet.devops.lowcode.wiki.controller;

import javax.annotation.Resource;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import top.bitplanet.devops.support.core.helper.R;
import top.bitplanet.devops.support.core.framework.page.TYPageQuery;
import top.bitplanet.devops.support.core.framework.page.TYPageResp;
import top.bitplanet.devops.lowcode.wiki.service.IWikiClassificationService;
import top.bitplanet.devops.lowcode.wiki.dto.WikiClassificationResp;
import top.bitplanet.devops.lowcode.wiki.dto.query.WikiClassificationQuery;
/**
 * <p>
 * wiki分类 前端控制器
 * </p>
 *
 * @author Le
 * @date 2022-03-07
 */
@RestController
@RequestMapping("/wiki/wikiClassification")
public class WikiClassificationController {
    @Resource
    private IWikiClassificationService wikiClassificationService;

    /**
     * 新增 wiki分类
     * @param query
     * @return
     */
    @PostMapping("")
    public R<WikiClassificationQuery> add(@RequestBody @Valid WikiClassificationQuery query) {
        query.setWikiSum(0);
        boolean result = wikiClassificationService.saveByQuery(query);
        if (!result) {
            return R.fail("保存失败！");
        }
        return R.success(query);
    }

    /**
     * 根据 id 删除 wiki分类
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public R<Long> delete(@PathVariable("id") Long id) {
        boolean result = wikiClassificationService.removeById(id);
        if (!result) {
            return R.fail("删除失败！");
        }
        return R.success(id);
    }

    /**
     * 根据id 修改 wiki分类
     * @param id
     * @param query
     * @return
     */
    @PutMapping("/{id}")
    public R<String> update(@PathVariable("id") Long id, @RequestBody @Valid WikiClassificationQuery query) {
        query.setId(id);
        boolean result = wikiClassificationService.updateByQuery(query);
        if (!result) {
            return R.fail("修改失败！");
        }
        return R.success("修改成功");
    }

    /**
     * 根据id 查询 wiki分类 详情
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<WikiClassificationResp> detail(@PathVariable("id") Long id) {
        WikiClassificationResp resp = wikiClassificationService.getRespById(id);
        return R.success(resp);
    }

    /**
     * 通用分页查询
     * @param tyPageQuery
     * @return
     */
    @GetMapping("/page")
    public R<TYPageResp<WikiClassificationResp>> page(TYPageQuery<WikiClassificationQuery> tyPageQuery,WikiClassificationQuery query) {
        tyPageQuery.setQuery(query);
        TYPageResp<WikiClassificationResp> page = wikiClassificationService.pageResp(tyPageQuery);
        return R.success(page);
    }


}