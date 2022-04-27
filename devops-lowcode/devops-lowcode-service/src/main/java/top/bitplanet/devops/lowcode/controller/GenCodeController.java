package top.bitplanet.devops.lowcode.controller;

import top.bitplanet.devops.lowcode.constants.ProjectConfig;
import top.bitplanet.devops.lowcode.dto.query.ProjectMetaDataQuery;
import top.bitplanet.devops.lowcode.helper.Gencode4MybatisplusHelper;
import top.bitplanet.devops.lowcode.entity.DatasourceInfo;
import top.bitplanet.devops.lowcode.entity.PgTableColumn;
import top.bitplanet.devops.lowcode.entity.PgTableInfo;
import top.bitplanet.devops.lowcode.initialconfig.entity.ModuleInfo;
import top.bitplanet.devops.lowcode.initialconfig.entity.ProductInfo;
import top.bitplanet.devops.lowcode.initialconfig.service.IModuleInfoService;
import top.bitplanet.devops.lowcode.initialconfig.service.IProductInfoService;
import top.bitplanet.devops.lowcode.mapper.GenCodeMapper;
import top.bitplanet.devops.lowcode.service.IDatasourceInfoService;
import top.bitplanet.devops.lowcode.service.IGenCodeService;
import top.bitplanet.devops.support.core.helper.*;
import top.bitplanet.devops.uaa.dto.UserResp;
import top.bitplanet.devops.uaa.dto.query.UserQuery;
import top.bitplanet.devops.uaa.feign.UserFeign;
import top.bitplanet.devops.lowcode.dto.query.DataSourceInfoQuery;
import top.bitplanet.devops.lowcode.dto.query.FormUIComponentInfoQuery;
import top.bitplanet.devops.lowcode.dto.query.GenCodeQuery;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/rapid")
public class GenCodeController {

    static final String OSS_ROOT = "oss";

    @Autowired
    private IGenCodeService genCodeService;
    @Autowired
    private IDatasourceInfoService datasourceInfoService;
    @Autowired
    private UserFeign userFeign;
    @Autowired
    private ServerProperties serverProperties;
    @Autowired
    private IModuleInfoService moduleInfoService;
    @Autowired
    private IProductInfoService productInfoService;

    @GlobalTransactional(timeoutMills = 300000, name = "spring-cloud-demo-tx")
    @GetMapping("/test")
    public Object test() {
        String xid = RootContext.getXID();
        log.info("进入lowcode....." + xid);
        R<UserResp> detail = userFeign.detail(1471363275779506177L);
        UserQuery userQuery = new UserQuery();
        userQuery.setUsername("对对对");
        userQuery.setEmail("1@a.com");
        userFeign.add(userQuery);
        DatasourceInfo info = new DatasourceInfo();
        info.setDatabase("测试");
        datasourceInfoService.save(info);
        // System.out.println(1/0);
        return detail;
    }

    /**
     * 生成代码，压缩zip并下载
     * @param query
     * @param request
     * @return
     * @throws SQLException
     */
    @PostMapping("/downloadCode")
    public Object gen(@RequestBody GenCodeQuery query, HttpServletRequest request) throws SQLException {
        DatasourceInfo info = datasourceInfoService.getById(query.getDatasourceInfoId());
        // 数据源信息
        DataSourceInfoQuery dataSourceInfoQuery = info.convertToQuery();
        String realPath = request.getServletContext().getRealPath("");
        log.info("项目真实路径==>{}",realPath);
        // 相对路径
        String relativePath = OSS_ROOT + "/" + UuIdHelper.randomUUID();
        // 项目真实路径
        String projectPath = realPath + "/" + relativePath;
        try {
            projectPath = Gencode4MybatisplusHelper.genCode(dataSourceInfoQuery,query,projectPath);
            File zip = ZipHelper.zip(projectPath);
            String zipPath =  OSS_ROOT + "/" + zip.getName();
            return R.success(serverProperties.getServlet().getContextPath() + "/" + zipPath);
        } catch (Exception e) {
            log.error("生成代码异常：",e);
            return R.fail("生成代码异常！");
        }
    }

    /**
     * 安装代码，使用coder在线合并
     * @param query
     * @param request
     * @return
     * @throws SQLException
     */
    @PostMapping("/installCode")
    public Object installCode(@RequestBody GenCodeQuery query, HttpServletRequest request) {
        DatasourceInfo info = datasourceInfoService.getById(query.getDatasourceInfoId());
        // 数据源信息
        DataSourceInfoQuery dataSourceInfoQuery = info.convertToQuery();
        String realPath = request.getServletContext().getRealPath("");
        // 项目路径
        String projectPath = realPath + "/" + OSS_ROOT + "/" + UuIdHelper.randomUUID();
        try {
            // 以模块id对应的数据库信息为准
            ModuleInfo moduleInfo = moduleInfoService.getById(query.getModuleInfoId());
            ProjectMetaDataQuery metaDataQuery = query.getMetaDataQuery();
            metaDataQuery.setDescription(moduleInfo.getDescription());
            metaDataQuery.setModuleName(moduleInfo.getModuleName());
            // 产品信息
            ProductInfo productInfo = productInfoService.getById(moduleInfo.getProductId());
            metaDataQuery.setCompanyName(productInfo.getCompanyName());
            metaDataQuery.setProductName(productInfo.getProductName());
            // 生成代码
            projectPath = Gencode4MybatisplusHelper.genCode(dataSourceInfoQuery,query,projectPath);
            // 生成的后台代码路径
            String backCodePath = projectPath + "/" + query.getMetaDataQuery().getProductName() + "-" + query.getMetaDataQuery().getModuleHyphen();
            // 开始安装后台代码 ，复制git目录
            String backGitDir = moduleInfo.getBackGitDir();
            String gitDest = Gencode4MybatisplusHelper.pullAndCopy(backGitDir, ProjectConfig.GIT_COPY_DIR);
            // 合并代码
            String moduleDir = gitDest + moduleInfo.getBackModuleDir();
            FileHelper.copyContent(backCodePath ,moduleDir,query.isOverride());
            // 安装前端代码 & 合并
            String frontGitSrc = moduleInfo.getFrontGitDir();
            String frontGitDest = Gencode4MybatisplusHelper.pullAndCopy(frontGitSrc,ProjectConfig.GIT_COPY_DIR);
            String viewDir = frontGitDest + moduleInfo.getFrontModuleDir();
            String webPath = projectPath + "/web";
            FileHelper.copyContent(webPath,viewDir,query.isOverride());
            return R.success(new String[]{gitDest,frontGitDest});
        } catch (Exception e) {
            log.error("生成代码异常：",e);
            return R.fail(e.getMessage());
        }
    }

    /**
     * 根据表单生成sql
     * @return
     */
    @PostMapping("/genSql")
    public Object genSqlByForm(@RequestBody List<FormUIComponentInfoQuery> querys,String tableName) {
        log.info(querys.toString());
        String sql = genCodeService.genSqlByForm(querys,tableName);
        return R.success(sql);
    }

    /**
     *
     * @return
     */
    @PostMapping("/database/{id}/executeSql")
    public Object executeSql(@RequestBody String sql,@PathVariable("id") Long databaseId) {
        Connection connection = datasourceInfoService.getConnectionById(databaseId);
        if (connection == null) {
            return  R.fail("无法连接，请检查数据源信息配置是否正确！");
        }
        boolean result;
        try {
            result = DBHelper.executeSql(connection, sql);
        } catch (SQLException e) {
            log.error("执行生成语句sql失败:",e);
            return R.fail("执行失败，请检查sql格式");
        }
        return R.success("执行成功！" + result);
    }

    /**
     * 获取选中数据库下的所有表
     * @param id
     * @return
     */
    @GetMapping("/database/{id}")
    public R<List<PgTableInfo>> showTables(@PathVariable("id") Long id) {
        Connection connection = datasourceInfoService.getConnectionById(id);
        if (connection == null) {
            return  R.fail("无法连接，请检查数据源信息配置是否正确！");
        }
        List<PgTableInfo> allTables = genCodeService.findAllTables(connection, GenCodeMapper.SQL_SELECT_ALL_TABLES);
        return R.success("success",allTables);
    }

    /**
     * 获取表结构信息
     * @param id
     * @return
     */
    @GetMapping("/database/{id}/{tableName}")
    public Object showTables(@PathVariable("id") Long id,@PathVariable("tableName")String tableName) {
        DatasourceInfo info = datasourceInfoService.getById(id);
        DataSourceInfoQuery query = info.convertToQuery();
        // 建立连接
        Connection connection = DBHelper.getConnection(query.getJdbcUrl(), query.getUsername(), query.getPassword());
        List<PgTableColumn> allTableColumns = genCodeService.findAllColumns(connection, GenCodeMapper.SQL_SELECT_ALL_COLUMNS,tableName);
        return R.success("success",allTableColumns);
    }


}
