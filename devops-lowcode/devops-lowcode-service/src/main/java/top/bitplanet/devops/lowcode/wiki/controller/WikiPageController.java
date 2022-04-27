package top.bitplanet.devops.lowcode.wiki.controller;

import javax.annotation.Resource;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import top.bitplanet.devops.support.core.helper.R;
import top.bitplanet.devops.support.core.framework.page.TYPageQuery;
import top.bitplanet.devops.support.core.framework.page.TYPageResp;
import top.bitplanet.devops.lowcode.wiki.service.IWikiPageService;
import top.bitplanet.devops.lowcode.wiki.dto.WikiPageResp;
import top.bitplanet.devops.lowcode.wiki.dto.query.WikiPageQuery;
/**
 * <p>
 * wiki知识库page页 前端控制器
 * </p>
 *
 * @author Le
 * @date 2022-02-22
 */
@RestController
@RequestMapping("/wiki/wikiPage")
public class WikiPageController {
    @Resource
    private IWikiPageService wikiPageService;

    /**
     * 新增 wiki知识库page页
     * @param query
     * @return
     */
    @PostMapping("")
    public R<WikiPageQuery> add(@RequestBody @Valid WikiPageQuery query) {
        boolean result = wikiPageService.saveByQuery(query);
        if (!result) {
            return R.fail("保存失败！");
        }
        return R.success(query);
    }

    /**
     * 根据 id 删除 wiki知识库page页
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public R<Long> delete(@PathVariable("id") Long id) {
        boolean result = wikiPageService.removeById(id);
        if (!result) {
            return R.fail("删除失败！");
        }
        return R.success(id);
    }

    /**
     * 根据id 修改 wiki知识库page页
     * @param id
     * @param query
     * @return
     */
    @PutMapping("/{id}")
    public R<String> update(@PathVariable("id") Long id, @RequestBody @Valid WikiPageQuery query) {
        query.setId(id);
        boolean result = wikiPageService.updateByQuery(query);
        if (!result) {
            return R.fail("修改失败！");
        }
        return R.success("修改成功");
    }

    /**
     * 根据id 查询 wiki知识库page页 详情
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<WikiPageResp> detail(@PathVariable("id") Long id) {
        WikiPageResp resp = wikiPageService.getRespById(id);
        return R.success(resp);
    }

    /**
     * 通用分页查询
     * @param tyPageQuery
     * @return
     */
    @GetMapping("/page")
    public R<TYPageResp<WikiPageResp>> page(TYPageQuery<WikiPageQuery> tyPageQuery) {
        TYPageResp<WikiPageResp> page = wikiPageService.pageResp(tyPageQuery);
        return R.success(page);
    }


}