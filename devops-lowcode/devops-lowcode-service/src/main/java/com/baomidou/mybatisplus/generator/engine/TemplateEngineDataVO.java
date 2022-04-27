package com.baomidou.mybatisplus.generator.engine;

import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

/**
 * <p>
 *  引擎数据导出
 * </p>
 *
 * @author Le
 * @version 1.0.0
 * @since 2021/12/23 17:19
 */
@Data
@AllArgsConstructor
public class TemplateEngineDataVO {

    private TableInfo tableInfo;

    Map<String, Object> objectMap;

}
