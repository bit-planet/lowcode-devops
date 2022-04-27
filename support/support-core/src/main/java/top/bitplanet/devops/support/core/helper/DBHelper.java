package top.bitplanet.devops.support.core.helper;

import cn.hutool.db.DbUtil;
import cn.hutool.db.handler.BeanListHandler;
import cn.hutool.db.sql.SqlExecutor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * **************************************************************
 * @描述        : 简单数据库操作帮助类
 * @作者        : 乐胜
 * @版本        : v1.0
 * @日期        : 2021/11/21
 */
public class DBHelper {

    public static Connection getConnection(String jdbcUrl,String username,String password) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static <T>  List<T> querySql(Connection connection, String querySql, Class<T> tClass, Object... param) throws SQLException {
        List<T> list = SqlExecutor.query(connection, querySql,
                new BeanListHandler<T>(tClass),param);
        return list;
    }

    /**
     * 执行sql
     * @param connection
     * @param sql
     * @param param
     * @return
     * @throws SQLException
     */
    public static boolean executeSql(Connection connection, String sql, Object... param) throws SQLException {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            boolean result = statement.execute(sql);
            return result;
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }



}
