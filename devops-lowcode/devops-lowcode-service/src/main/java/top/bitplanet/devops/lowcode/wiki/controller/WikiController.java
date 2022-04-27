package top.bitplanet.devops.lowcode.wiki.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import top.bitplanet.devops.lowcode.wiki.po.Tree;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import top.bitplanet.devops.support.core.helper.R;
import top.bitplanet.devops.support.core.framework.page.TYPageQuery;
import top.bitplanet.devops.support.core.framework.page.TYPageResp;
import top.bitplanet.devops.lowcode.wiki.service.IWikiService;
import top.bitplanet.devops.lowcode.wiki.dto.WikiResp;
import top.bitplanet.devops.lowcode.wiki.dto.query.WikiQuery;
/**
 * <p>
 * wiki知识库 前端控制器
 * </p>
 *
 * @author Le
 * @date 2022-02-21
 */
@RestController
@RequestMapping("/wiki/wiki")
public class WikiController {
    @Resource
    private IWikiService wikiService;

    /**
     * 新增 wiki知识库
     * @param query
     * @return
     */
    @PostMapping("")
    public R<String> add(@RequestBody @Valid WikiQuery query) {

        boolean result = wikiService.saveByQuery(query);
        if (!result) {
            return R.fail("保存失败！");
        }
        return R.success("保持成功");
    }

    /**
     * 根据 id 删除 wiki知识库
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public R<Long> delete(@PathVariable("id") Long id) {
        boolean result = wikiService.removeById(id);
        if (!result) {
            return R.fail("删除失败！");
        }
        return R.success(id);
    }

    /**
     * 根据id 修改 wiki知识库
     * @param id
     * @param query
     * @return
     */
    @PutMapping("/{id}")
    public R<String> update(@PathVariable("id") Long id, @RequestBody @Valid WikiQuery query) {
        query.setId(id);
        boolean result = wikiService.updateByQuery(query);
        if (!result) {
            return R.fail("修改失败！");
        }
        return R.success("修改成功");
    }

    /**
     * 根据id 查询 wiki知识库 详情
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<WikiResp> detail(@PathVariable("id") Long id) {
        WikiResp resp = wikiService.getRespById(id);
        return R.success(resp);
    }

    /**
     * 通用分页查询
     * @param tyPageQuery
     * @return
     */
    @GetMapping("/page")
    public R<TYPageResp<WikiResp>> page(TYPageQuery<WikiQuery> tyPageQuery) {
        TYPageResp<WikiResp> page = wikiService.pageResp(tyPageQuery);
        return R.success(page);
    }


}