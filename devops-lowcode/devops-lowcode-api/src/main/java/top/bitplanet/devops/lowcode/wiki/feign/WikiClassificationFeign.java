package top.bitplanet.devops.lowcode.wiki.feign;

import top.bitplanet.devops.support.core.framework.page.TYPageQuery;
import top.bitplanet.devops.support.core.framework.page.TYPageResp;
import top.bitplanet.devops.support.core.helper.R;
import top.bitplanet.devops.lowcode.wiki.dto.WikiClassificationResp;
import top.bitplanet.devops.lowcode.wiki.dto.query.WikiClassificationQuery;
import org.springframework.cloud.openfeign.FeignClient;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * wiki分类 feign 对象
 * </p>
 *
 * @author Le
 * @since 2022-03-07
 */
@FeignClient(contextId = "lowcodeWikiClassificationFeign",name = "lowcode",path = "/wiki/wikiClassification")
public interface WikiClassificationFeign {

    /**
    * 新增 wiki分类
    * @param query
    * @return
    */
    @PostMapping("")
    public R<String> add(@RequestBody @Valid WikiClassificationQuery query);

    /**
     * 根据 id 删除 wiki分类
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public R<Long> delete(@PathVariable("id") Long id);

    /**
     * 根据id 修改 wiki分类
     * @param id
     * @param query
     * @return
     */
    @PutMapping("/{id}")
    public R<String> update(@PathVariable("id") Long id, @RequestBody WikiClassificationQuery query);

    /**
     * 根据id 查询wiki分类详情
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<WikiClassificationResp> detail(@PathVariable("id") Long id);

    /**
     * 通用分页查询
     * @param tyPageQuery
     * @return
     */
    @GetMapping("/page")
    public R<TYPageResp<WikiClassificationResp>> page(TYPageQuery<WikiClassificationQuery> tyPageQuery);

}