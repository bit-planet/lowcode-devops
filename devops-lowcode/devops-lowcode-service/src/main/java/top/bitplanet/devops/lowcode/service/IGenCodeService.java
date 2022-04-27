package top.bitplanet.devops.lowcode.service;


import top.bitplanet.devops.lowcode.entity.PgTableColumn;
import top.bitplanet.devops.lowcode.entity.PgTableInfo;
import top.bitplanet.devops.lowcode.dto.query.DataSourceInfoQuery;
import top.bitplanet.devops.lowcode.dto.query.FormUIComponentInfoQuery;

import java.sql.Connection;
import java.util.List;

public interface IGenCodeService {

     void genCode4Mybatisplus(DataSourceInfoQuery query);


     /**
      * 查询指定数据库连接的所有的表
      * @param connection
      * @param sql
      * @return
      */
     List<PgTableInfo> findAllTables(Connection connection, String sql);

     /**
      * 查询列信息
      * @param connection
      * @param sql
      * @param params
      * @return
      */
     List<PgTableColumn> findAllColumns(Connection connection, String sql, Object... params);

     /**
      * 根据表单配置信息生成 创建表sql
      * @param querys     （组件）字段信息
      * @param tableName  表名
      * @return
      */
     String genSqlByForm(List<FormUIComponentInfoQuery> querys,String tableName);

}
