package top.bitplanet.devops.lowcode.helper;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.MyFastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.ConstVal;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.RestUrls;
import com.baomidou.mybatisplus.generator.engine.TemplateEngineDataVO;
import top.bitplanet.devops.support.core.helper.CollectionHelper;
import top.bitplanet.devops.support.core.helper.FileHelper;
import top.bitplanet.devops.support.core.helper.InitialHelper;
import top.bitplanet.devops.support.core.helper.StringHelper;
import top.bitplanet.devops.lowcode.dto.query.DataSourceInfoQuery;
import top.bitplanet.devops.lowcode.dto.query.GenCodeQuery;
import top.bitplanet.devops.lowcode.dto.query.ProjectMetaDataQuery;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

/**
 * 代码生成帮助类
 * 依赖 mybatis plus generator 3.5.1
 */
@Slf4j
@Component
public class Gencode4MybatisplusHelper {


    /**
     *  获取velocity模板对象
     * @param templatePath 模板文件路径
     * @return
     */
    public static Template getTemplate(String templatePath) {
        Template template = null;
        Properties p = new Properties();
        p.setProperty(ConstVal.VM_LOAD_PATH_KEY, ConstVal.VM_LOAD_PATH_VALUE);
        p.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, StringPool.EMPTY);
        p.setProperty(Velocity.ENCODING_DEFAULT, ConstVal.UTF8);
        p.setProperty(Velocity.INPUT_ENCODING, ConstVal.UTF8);
        p.setProperty("file.resource.loader.unicode", StringPool.TRUE);
        VelocityEngine velocityEngine = new VelocityEngine(p);
        template = velocityEngine.getTemplate(templatePath, ConstVal.UTF8);
        return template;
    }

    /**
     * 根据模板生成文件
     * @param templatePath 模板文件路径
     * @param outPath      输出路径
     * @param objectMap    动态参数
     */
    public static void genFile(String templatePath, String outPath, Map<String, Object> objectMap) {
        File file = new File(outPath);
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            // 如果父文件夹不存在则创建
            parentFile.mkdirs();
        }
        Template template = getTemplate(templatePath);
        try (FileOutputStream fos = new FileOutputStream(outPath);
             OutputStreamWriter ow = new OutputStreamWriter(fos, ConstVal.UTF8);
             BufferedWriter writer = new BufferedWriter(ow)) {
            template.merge(new VelocityContext(objectMap), writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成后台代码
     * @param dataSourceInfoQuery  数据源
     * @param genCodeQuery         生成规则
     * @param projectPath          生成路径
     * @throws Exception
     * @Return 生成的项目路径
     */
    public static String genCode(DataSourceInfoQuery dataSourceInfoQuery, GenCodeQuery genCodeQuery, String projectPath) throws Exception {
        ProjectMetaDataQuery metaDataQuery = genCodeQuery.getMetaDataQuery();
        // 生成项目脚手架
        genProjectDirs(projectPath,metaDataQuery);
        /**
         * 生成MybatisPlus代码
         */
        // main 目录
        String mainDir = genModuleDir(projectPath,metaDataQuery,"service") + "/src/main";
        String apiMainDir = genModuleDir(projectPath,metaDataQuery,"api") +"/src/main";
        // 执行mybatisplus 生成代码，并获取模板数据
        List<TemplateEngineDataVO> dataVOList = genMybatisPlusBean(dataSourceInfoQuery,mainDir,metaDataQuery,
                                                            genCodeQuery.getTableNames(),genCodeQuery.getTablePrefix());
        if (CollectionHelper.isEmpty(dataVOList)) {
            throw new Exception("数据库中没有找到对应的表，建议先执行sql！");
        }
        /**
         * 扩展 api  & api * dto & vo 目录
         */
        String company = metaDataQuery.getCompanyName().replaceAll("\\.", "/");
        String javaModulePath =  "/" + company
                + "/" +metaDataQuery.getProductName()
                + "/" +metaDataQuery.getModuleFolder();
        String javaPackagePath = javaModulePath;
        if (StringHelper.isNotEmpty(metaDataQuery.getSubModuleName())) {
            javaPackagePath = javaModulePath + "/" + metaDataQuery.getSubModuleName();
        }
        // java 包路径
        String apiJavaBase = apiMainDir+"/java" + javaPackagePath ;
        String serviceJavaBase = mainDir +"/java" + javaPackagePath;
        /**
         *  生成相关DTO & feign 类
         */
        // 获取模板引擎 拿到数据
        for (TemplateEngineDataVO vo : dataVOList) {
            Map<String, Object> objectMap = vo.getObjectMap();
            // 移除 @TableName import 引用
            TableInfo tableInfo = (TableInfo) objectMap.get("table");
            tableInfo.getImportPackages().remove("com.baomidou.mybatisplus.annotation.TableName");
            // query & resp 生成公共字段
            tableInfo.getFields().addAll(tableInfo.getCommonFields());
            Set<String> importPackages = tableInfo.getImportPackages();
            importPackages.add("java.time.LocalDateTime");
            importPackages.remove("top.bitplanet.devops.support.mybatisplus.base.BaseEntity");
            // query 和 resp 类 移除 baseEntity
            objectMap.put("superEntityClass",null);
            // 添加rest url规则
            Map<String,String> aPackage = (Map) objectMap.get("package");
            String moduleName = aPackage.get("ModuleName");
            RestUrls restUrls = new RestUrls();
            // 兼容子模块,rest base地址  /子模块名/类名（首字母小写）
            String restBase = "";
            if (StringHelper.isNotEmpty(metaDataQuery.getSubModuleName())) {
                restBase += "/" + metaDataQuery.getSubModuleName();
            }
            restBase += "/" + InitialHelper.toLowerCase(tableInfo.getEntityName());
            restUrls.setContextPath("/" + metaDataQuery.getModuleFolder());
            restUrls.setBaseUrl(restBase);
            objectMap.put("rest",restUrls);
            // 项目信息
            objectMap.put("projectMetaData",metaDataQuery);
            // query 对象
            genFile("/templates/custom/module-api/query.java.vm",
                    apiJavaBase + "/dto/query/" + vo.getTableInfo().getEntityName() + "Query.java",objectMap);
            // resp 对象
            genFile("/templates/custom/module-api/resp.java.vm",
                    apiJavaBase + "/dto/" + vo.getTableInfo().getEntityName() + "Resp.java",objectMap);
            // feign 对象
            genFile("/templates/custom/module-api/feign.java.vm",
                    apiJavaBase + "/feign/" + vo.getTableInfo().getEntityName() + "Feign.java",objectMap);
            // controller
            genFile("/templates/mybatisplus/controller.java.vm",
                    serviceJavaBase + "/controller/" + vo.getTableInfo().getControllerName() + ".java",objectMap);
            /** 生成前端代码 */
            genFrontendCode(projectPath,objectMap,genCodeQuery);
        }
        Map<String, Object> objectMap = dataVOList.get(0).getObjectMap();
        // bootstrap.yml
        genFile("/templates/custom/bootstrap.yml.vm",
                mainDir + "/resources/bootstrap.yml",objectMap);
        // application.yml
        genFile("/templates/custom/application.yml.vm",
                mainDir + "/resources/application.yml",objectMap);
        // log4j2-spring.xml
        genFile("/templates/custom/log4j2-spring.xml.vm",
                mainDir + "/resources/log4j2-spring.xml",objectMap);
        // spring boot application
        String applicationClassName = metaDataQuery.getModuleApplicationClassName();
        objectMap.put("applicationName",applicationClassName);
        genFile("/templates/custom/bootApplication.java.vm",
                mainDir + "/java/" + javaModulePath + "/" + applicationClassName + ".java",objectMap);
        // 生成 META-INF/mavenInfo.properties
        genFile("/templates/custom/module-service/META-INF/mavenInfo.properties.vm",
                mainDir + "/resources/META-INF/mavenInfo.properties",objectMap );
       return projectPath  ;
    }


    /**
     * 生成前端代码
     * @return
     */
    public static String genFrontendCode(String projectPath,Map<String, Object> objectMap, GenCodeQuery genCodeQuery) {
        ProjectMetaDataQuery metaDataQuery = genCodeQuery.getMetaDataQuery();
        TableInfo tableInfo = (TableInfo) objectMap.get("table");
        String webRootDir = projectPath + "/web/" + metaDataQuery.getModuleFolder() ;
        if (StringHelper.isNotEmpty(metaDataQuery.getSubModuleName())) {
            webRootDir += "/" + metaDataQuery.getSubModuleName();
        }
        webRootDir += "/" + InitialHelper.toLowerCase(tableInfo.getEntityName());
        genFile("/templates/custom/web/index.vue.vm",
                webRootDir + "/index.vue",objectMap );
        // 生成 component
        String componentDir = webRootDir + "/component";
        Map map = new HashMap();
        map.put("addVue",genCodeQuery.getAddVue());
        map.put("editVue",genCodeQuery.getEditVue());
        genFile("/templates/custom/web/add.vue.vm",
                componentDir + "/addDialog.vue",map );
        genFile("/templates/custom/web/edit.vue.vm",
                componentDir + "/editDialog.vue",map );
        return "";
    }

    /**
     * 生成项目目录
     *
     * @param rootPath   根路径
     * @param metaQuery
     */
    public static void genProjectDirs(String rootPath, ProjectMetaDataQuery metaQuery) throws Exception {
        // 根目录
        String apiDir =  genModuleDir(rootPath,metaQuery,"api");
        FileHelper.mkdirs(apiDir);
        String serviceDir = genModuleDir(rootPath,metaQuery,"service");
        FileHelper.mkdirs(serviceDir);
        // 基本 maven 项目结构
        mavenStructure(apiDir);
        mavenStructure(serviceDir);
        /**
         * 生成  pom.xml
         */
        Map<String, Object> objectMap = new HashMap();
        objectMap.put("projectMetaData",metaQuery);
        //objectMap.put("groupName",groupName);
        // 生成api pom文件
        genFile("/templates/custom/api.pom.xml.vm",apiDir+"/pom.xml",objectMap);
        // 生成service pom文件
        genFile("/templates/custom/service.pom.xml.vm",serviceDir+"/pom.xml",objectMap);
        // 生成聚合pom文件
        genFile("/templates/custom/aggregator.pom.xml.vm",
                rootPath +"/" +metaQuery.getProductName() + "-" + metaQuery.getModuleHyphen() +"/pom.xml" ,objectMap);
    }

    /**
     * 生成模块目录
     * @param rootPath 根目录
     * @param suffix 后缀 ： 通常是 "api" 或者 "service"
     * @return
     */
    private static String genModuleDir(String rootPath,ProjectMetaDataQuery metaQuery, String suffix) {
        String moduleHyphen = metaQuery.getModuleHyphen();
        // 支持多级模块
        String productName = metaQuery.getProductName();
        return rootPath + "/" + productName + "-" + moduleHyphen + "/" + productName + "-" + moduleHyphen + "-" + suffix;
    }

    /**
     * maven标准工程结构
     * @param baseDir
     */
    private static void mavenStructure(String baseDir) {
        // main 目录
        String main = baseDir + "/src/main";
        FileHelper.mkdirs(main);
        // main -> java  & resources
        String main_java = main + "/java";
        FileHelper.mkdirs(main_java);
        String main_resources = main + "/resources";
        FileHelper.mkdirs(main_resources);
        // test 目录
        String test = baseDir + "/src/test";
        FileHelper.mkdirs(test);
        // main -> java
        String test_java = test + "/java";
        FileHelper.mkdirs(test_java);
    }

    /**
     * 生成MybatisPlus 代码 Bean + xml
     * @param query
     * @param mainDir
     * @param metaDataQuery
     */
    public static List<TemplateEngineDataVO> genMybatisPlusBean(DataSourceInfoQuery query,String mainDir,
                            ProjectMetaDataQuery metaDataQuery,String[] tableNames,String... tablePrefix) {
        MyFastAutoGenerator fastAutoGenerator = MyFastAutoGenerator.create(query.getJdbcUrl(), query.getUsername(), query.getPassword());
        List<TemplateEngineDataVO> dataVOList = fastAutoGenerator
                .globalConfig(builder -> {
                    builder.author("Le")
                            .enableSwagger()
                            .fileOverride()
                            .disableOpenDir()
                            .outputDir(mainDir + "/java");
                })
                .packageConfig(builder -> {
                    String moduleName = metaDataQuery.getModuleName();
                    String mapperDir = "/resources/mapper";
                    if (StringHelper.isNotEmpty(metaDataQuery.getSubModuleName())) {
                        moduleName += "." + metaDataQuery.getSubModuleName();
                        mapperDir += "/" + metaDataQuery.getSubModuleName();
                    }
                    builder.parent(metaDataQuery.getCompanyName() + "." + metaDataQuery.getProductName())
                            .moduleName(moduleName)
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, mainDir + mapperDir));
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tableNames)
                            .addTablePrefix(tablePrefix)
                            .entityBuilder().enableLombok()
                            .logicDeleteColumnName("delete_flag")
                            .superClass("top.bitplanet.devops.support.mybatisplus.base.BaseEntity")
                            .addSuperEntityColumns("create_time","update_time","create_user_id","update_user_id","delete_flag")
                            .mapperBuilder().enableMapperAnnotation()
                            .enableBaseResultMap()
                            .enableBaseColumnList()
                            .controllerBuilder()
                            .enableRestStyle();
                })
                .templateConfig(builder -> {
                    builder.disable()
                            .entity("/templates/mybatisplus/entity.java.vm")
                            .mapper("/templates/mybatisplus/mapper.java.vm")
                            .mapperXml("/templates/mybatisplus/mapper.xml.vm")
                            .service("/templates/mybatisplus/service.java.vm")
                            .serviceImpl("/templates/mybatisplus/serviceImpl.java.vm")
                            // controller 自定义改为生成
                            // .controller("/templates/mybatisplus/controller.java.vm")
                    ;
                })
                .execute();
        return dataVOList;
    }

    /**
     * 执行git pull，保持源码更新
     * @param gitRootDir
     * @return
     */
    public static boolean gitPull(String gitRootDir) {
        // todo 补充兼容windows 写法  Runtime.getRuntime().exec(new String[]{ "cmd", "/c", cmds});


        String[] cmd = {"/bin/sh","-c","cd " + gitRootDir + " && git fetch --all && git reset --hard HEAD && git pull"};
        //String cmd = "sh /Users/neo/Desktop/t_while.sh";
        try {
            Process p = Runtime.getRuntime().exec(cmd);
            if (p.waitFor() != 0) {
                log.error("执行\"" + cmd + "\"时返回值=" + p.exitValue());
                // 输出错误信息
                BufferedReader errorReader = new BufferedReader(new InputStreamReader(p.getErrorStream()));
                String t ;
                while ((t = errorReader.readLine()) != null) {
                   log.error(t);
                }
                return false;
            }
            log.info("git pull 执行成功！");
            return true;
        } catch (IOException | InterruptedException e) {
            log.error("执行git pull命令异常：",e);
            return false;
        }
    }

    /**
     * 拉取并
     * @param gitRootDir
     * @param destRoot   目标根目录（此为顶级目录，在此文件夹下根据git文件夹名称创建一个copy）
     * @return
     */
    public static String pullAndCopy(String gitRootDir,String destRoot) throws Exception {
        // git pull命令
        boolean pullSuccess = gitPull(gitRootDir);
        if (!pullSuccess) {
            // TODO 暂时不强制更新git
            // throw new Exception("执行git pull命令失败！");
        }
        File file = new File(gitRootDir);
        // 新的文件名
        String newFileName = file.getName() + "-" +System.nanoTime();
        destRoot += "/" + newFileName;
        return FileHelper.copyContent(gitRootDir,destRoot,false);
    }

}
