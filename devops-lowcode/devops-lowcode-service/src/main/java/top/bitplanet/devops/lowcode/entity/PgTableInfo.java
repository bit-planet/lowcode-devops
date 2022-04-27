package top.bitplanet.devops.lowcode.entity;

import lombok.Data;

@Data
public class PgTableInfo {

    /** 表名 */
    private String tableName;
    /** 库名 */
    private String tableCatalog;
    /** 模式（Schema） */
    private String tableSchema;
    /** oid */
    private Integer oid;
    /** 表类型 */
    private String tableType;
    /** 表注释 */
    private String tableDescription;



}
