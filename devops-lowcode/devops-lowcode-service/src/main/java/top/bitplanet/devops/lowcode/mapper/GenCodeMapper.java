package top.bitplanet.devops.lowcode.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GenCodeMapper extends BaseMapper<Object> {

    /**
     * 查询所有的表
     */
    String SQL_SELECT_ALL_TABLES = "SELECT\n" +
            "table_name ," +
            "table_catalog ," +
            "table_schema," +
            "oid," +
            "table_type," +
            "obj_description ( oid, 'pg_class' )  as table_description " +
            "FROM " +
            "information_schema.tables t1," +
            "pg_class t2 " +
            "WHERE " +
            "table_type = 'BASE TABLE' " +
            "AND table_schema = 'public' " +
            "AND t1.table_name   NOT   LIKE   'pg%'  \n" +
            "AND t1.table_name NOT LIKE 'sql_%'\n" +
            "AND t1.table_name = t2.relname;";


    /**
     * 查某个表的所有字段名&字段类型&注释
     */
    String SQL_SELECT_ALL_COLUMNS = "SELECT " +
            "    a.attname as column_name, " +
            "    format_type(a.atttypid,a.atttypmod) as type, " +
            "    col_description(a.attrelid,a.attnum) as comment, " +
            "    a.attnotnull as notnull " +
            "FROM pg_class as c,pg_attribute as a " +
            "where c.relname = ? " +
            "and a.attrelid = c.oid and a.attnum>0;";


}
