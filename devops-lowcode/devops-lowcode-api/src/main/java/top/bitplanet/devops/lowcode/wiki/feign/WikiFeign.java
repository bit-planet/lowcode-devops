package top.bitplanet.devops.lowcode.wiki.feign;

import top.bitplanet.devops.support.core.framework.page.TYPageQuery;
import top.bitplanet.devops.support.core.framework.page.TYPageResp;
import top.bitplanet.devops.support.core.helper.R;
import top.bitplanet.devops.lowcode.wiki.dto.WikiResp;
import top.bitplanet.devops.lowcode.wiki.dto.query.WikiQuery;
import org.springframework.cloud.openfeign.FeignClient;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * wiki知识库 feign 对象
 * </p>
 *
 * @author Le
 * @since 2022-02-21
 */
@FeignClient(contextId = "lowcodeWikiFeign",name = "lowcode",path = "/wiki/wiki")
public interface WikiFeign {

    /**
    * 新增 wiki知识库
    * @param query
    * @return
    */
    @PostMapping("")
    public R<String> add(@RequestBody @Valid WikiQuery query);

    /**
     * 根据 id 删除 wiki知识库
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public R<Long> delete(@PathVariable("id") Long id);

    /**
     * 根据id 修改 wiki知识库
     * @param id
     * @param query
     * @return
     */
    @PutMapping("/{id}")
    public R<String> update(@PathVariable("id") Long id, @RequestBody WikiQuery query);

    /**
     * 根据id 查询wiki知识库详情
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<WikiResp> detail(@PathVariable("id") Long id);

    /**
     * 通用分页查询
     * @param tyPageQuery
     * @return
     */
    @GetMapping("/page")
    public R<TYPageResp<WikiResp>> page(TYPageQuery<WikiQuery> tyPageQuery);

}