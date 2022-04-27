/*
 * Copyright (c) 2011-2021, baomidou (jobob@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.baomidou.mybatisplus.generator;

import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.TemplateEngineDataVO;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 生成文件
 *
 * @author YangHu, tangguo, hubin
 * @since 2016-08-30
 */
public class MyAutoGenerator {

    private static final Logger logger = LoggerFactory.getLogger(MyAutoGenerator.class);

    /**
     * 配置信息
     */
    protected ConfigBuilder config;
    /**
     * 注入配置
     */
    protected InjectionConfig injection;
    /**
     * 数据源配置
     */
    private DataSourceConfig dataSource;
    /**
     * 数据库表配置
     */
    private StrategyConfig strategy;
    /**
     * 包 相关配置
     */
    private PackageConfig packageInfo;
    /**
     * 模板 相关配置
     */
    private TemplateConfig template;
    /**
     * 全局 相关配置
     */
    private GlobalConfig globalConfig;

    private MyAutoGenerator() {
        // 不推荐使用
    }

    /**
     * 构造方法
     *
     * @param dataSourceConfig 数据库配置
     * @since 3.5.0
     */
    public MyAutoGenerator(@NotNull DataSourceConfig dataSourceConfig) {
        //这个是必须参数,其他都是可选的,后续去除默认构造更改成final
        this.dataSource = dataSourceConfig;
    }

    /**
     * 注入配置
     *
     * @param injectionConfig 注入配置
     * @return this
     * @since 3.5.0
     */
    public MyAutoGenerator injection(@NotNull InjectionConfig injectionConfig) {
        this.injection = injectionConfig;
        return this;
    }

    /**
     * 生成策略
     *
     * @param strategyConfig 策略配置
     * @return this
     * @since 3.5.0
     */
    public MyAutoGenerator strategy(@NotNull StrategyConfig strategyConfig) {
        this.strategy = strategyConfig;
        return this;
    }

    /**
     * 指定包配置信息
     *
     * @param packageConfig 包配置
     * @return this
     * @since 3.5.0
     */
    public MyAutoGenerator packageInfo(@NotNull PackageConfig packageConfig) {
        this.packageInfo = packageConfig;
        return this;
    }

    /**
     * 指定模板配置
     *
     * @param templateConfig 模板配置
     * @return this
     * @since 3.5.0
     */
    public MyAutoGenerator template(@NotNull TemplateConfig templateConfig) {
        this.template = templateConfig;
        return this;
    }

    /**
     * 指定全局配置
     *
     * @param globalConfig 全局配置
     * @return this
     * @see 3.5.0
     */
    public MyAutoGenerator global(@NotNull GlobalConfig globalConfig) {
        this.globalConfig = globalConfig;
        return this;
    }

    /**
     * 设置配置汇总
     *
     * @param configBuilder 配置汇总
     * @return this
     * @since 3.5.0
     */
    public MyAutoGenerator config(@NotNull ConfigBuilder configBuilder) {
        this.config = configBuilder;
        return this;
    }

    /**
     * 生成代码
     */
    public void execute() {
        this.execute(null);
    }

    /**
     * 生成代码
     *
     * @param templateEngine 模板引擎
     */
    public List<TemplateEngineDataVO> execute(AbstractTemplateEngine templateEngine) {
        logger.debug("==========================准备生成文件...==========================");
        // 初始化配置
        if (null == config) {
            config = new ConfigBuilder(packageInfo, dataSource, strategy, template, globalConfig, injection);
        }
        if (null == templateEngine) {
            // 为了兼容之前逻辑，采用 Velocity 引擎 【 默认 】
            templateEngine = new VelocityTemplateEngine();
        }
        templateEngine.setConfigBuilder(config);
        // 模板引擎初始化执行文件输出
        AbstractTemplateEngine engine = templateEngine.init(config);
        engine.batchOutput().open();
        logger.debug("==========================文件生成完成！！！==========================");
        // 返回模板引擎数据，给mybatisplust 以外的用
        List<TemplateEngineDataVO> templateEngineDataVOList = new ArrayList<>();
        ConfigBuilder config = engine.getConfigBuilder();
        List<TableInfo> tableInfoList = config.getTableInfoList();
        tableInfoList.forEach(tableInfo -> {
            Map<String, Object> objectMap = engine.getObjectMap(config, tableInfo);
            TemplateEngineDataVO dataVO = new TemplateEngineDataVO(tableInfo, objectMap);
            templateEngineDataVOList.add(dataVO);
        });

        return templateEngineDataVOList;

    }

    /**
     * 开放表信息、预留子类重写
     *
     * @param config 配置信息
     * @return ignore
     */
    @NotNull
    protected List<TableInfo> getAllTableInfoList(@NotNull ConfigBuilder config) {
        return config.getTableInfoList();
    }

    public ConfigBuilder getConfig() {
        return config;
    }

    public InjectionConfig getInjectionConfig() {
        return injection;
    }

    public DataSourceConfig getDataSource() {
        return dataSource;
    }

    public StrategyConfig getStrategy() {
        return strategy;
    }

    public PackageConfig getPackageInfo() {
        return packageInfo;
    }

    public TemplateConfig getTemplate() {
        return template;
    }

    public GlobalConfig getGlobalConfig() {
        return globalConfig;
    }
}
