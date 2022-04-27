package top.bitplanet.devops.lowcode.initialconfig.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import top.bitplanet.devops.lowcode.constants.ProjectConfig;
import top.bitplanet.devops.lowcode.dto.FileDirInfo;
import top.bitplanet.devops.support.core.helper.StringHelper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import top.bitplanet.devops.support.core.helper.R;
import top.bitplanet.devops.support.core.framework.page.TYPageQuery;
import top.bitplanet.devops.support.core.framework.page.TYPageResp;
import top.bitplanet.devops.lowcode.initialconfig.service.IModuleInfoService;
import top.bitplanet.devops.lowcode.initialconfig.dto.ModuleInfoResp;
import top.bitplanet.devops.lowcode.initialconfig.dto.query.ModuleInfoQuery;

import java.io.File;
import java.util.*;

/**
 * <p>
 * 初始化配置 - 模块信息 前端控制器
 * </p>
 *
 * @author Le
 * @date 2022-02-07
 */
@RestController
@RequestMapping("/initialconfig/moduleInfo")
public class ModuleInfoController {
    @Resource
    private IModuleInfoService moduleInfoService;

    /**
     * 新增 初始化配置 - 模块信息
     * @param query
     * @return
     */
    @PostMapping("")
    public R<String> add(@RequestBody @Valid ModuleInfoQuery query) {
        boolean result = moduleInfoService.saveByQuery(query);
        if (!result) {
            return R.fail("保存失败！");
        }
        return R.success("保持成功");
    }

    /**
     * 根据 id 删除 初始化配置 - 模块信息
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public R<Long> delete(@PathVariable("id") Long id) {
        boolean result = moduleInfoService.removeById(id);
        if (!result) {
            return R.fail("删除失败！");
        }
        return R.success(id);
    }

    /**
     * 根据id 修改 初始化配置 - 模块信息
     * @param id
     * @param query
     * @return
     */
    @PutMapping("/{id}")
    public R<String> update(@PathVariable("id") Long id, @RequestBody @Valid ModuleInfoQuery query) {
        query.setId(id);
        boolean result = moduleInfoService.updateByQuery(query);
        if (!result) {
            return R.fail("修改失败！");
        }
        return R.success("修改成功");
    }

    /**
     * 根据id 查询 初始化配置 - 模块信息 详情
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<ModuleInfoResp> detail(@PathVariable("id") Long id) {
        ModuleInfoResp resp = moduleInfoService.getRespById(id);
        return R.success(resp);
    }

    /**
     * 通用分页查询
     * @param tyPageQuery
     * @return
     */
    @GetMapping("/page")
    public R<TYPageResp<ModuleInfoResp>> page(TYPageQuery<ModuleInfoQuery> tyPageQuery) {
        TYPageResp<ModuleInfoResp> page = moduleInfoService.pageResp(tyPageQuery);
        return R.success(page);
    }

    @GetMapping("/listByProductId")
    public R page(Long productId) {
        Map map = new HashMap();
        map.put("product_id",productId);
        List list = moduleInfoService.listByMap(map);
        return R.success(list);
    }

    /**
     * 配置路径
     * @return
     */
    @GetMapping("/merge/config/dirs")
    public R mergeConfigDirs(String rootDir) {
        FileDirInfo fileDirInfo = new FileDirInfo();
        if (StringHelper.isEmpty(rootDir)) {
            fileDirInfo.setName(ProjectConfig.GIT_BASE_DIR);
        } else {
            fileDirInfo.setName(rootDir);
        }
        fileDirInfo.setFile(new File(fileDirInfo.getName()));
        listDirs(fileDirInfo,5);
        return R.success(fileDirInfo);
    }




    public static void listDirs(FileDirInfo fileDirInfo, int maxStratum) {
        File file = fileDirInfo.getFile();
        File[] files = file.listFiles();
        int currentStratum = fileDirInfo.getStratum() + 1;
        if (files == null || files.length == 0 || currentStratum > maxStratum) {
            return;
        }
        if (fileDirInfo.getChildren() == null) {
            fileDirInfo.setChildren(new ArrayList<>());
        }
        Arrays.stream(files).filter(f -> {
            if (!f.isDirectory()) {
                return false;
            }
            return true;
        }).forEach(f -> {
            String name = f.getName();
            FileDirInfo child = new FileDirInfo();
            child.setName(name);
            child.setStratum(currentStratum);
            child.setFile(f);
            fileDirInfo.getChildren().add(child);
            listDirs(child,maxStratum);
        });
    }



}