package top.bitplanet.devops.lowcode.controller;


import top.bitplanet.devops.lowcode.entity.DatasourceInfo;
import top.bitplanet.devops.lowcode.service.IDatasourceInfoService;
import top.bitplanet.devops.support.core.helper.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 数据源信息 前端控制器
 * </p>
 *
 * @author Le
 * @since 2021-11-18
 */
@RestController
@RequestMapping("/datasourceInfo")
public class DatasourceInfoController {
    @Autowired
    private IDatasourceInfoService datasourceInfoService;


    @PostMapping("/save")
    public R save(@RequestBody DatasourceInfo info) {
        info.setDeleteFlag(false);
        info.setCreateUserId(1L);
        info.setCreateTime(LocalDateTime.now());
        info.setUpdateUserId(1L);
        info.setUpdateTime(LocalDateTime.now());
        datasourceInfoService.save(info);
        return R.success("success",new StringBuffer());
    }

    @GetMapping("/list")
    public Object list() {
        List<DatasourceInfo> list = datasourceInfoService.list();
        return R.success("success",list);
    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable("id")Long id) {
        datasourceInfoService.removeById(id);
        return R.success("success",new StringBuffer());
    }



}