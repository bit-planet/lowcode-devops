package top.bitplanet.devops.lowcode.dto.query;

import lombok.Data;

/**
 * 生成代码入参
 */
@Data
public class GenCodeQuery {

    /** 数据源ID */
    private Long datasourceInfoId;
    /** 模块信息ID  */
    private Long moduleInfoId;
    /** 项目源信息 */
    private ProjectMetaDataQuery metaDataQuery;
    /** 表名 */
    private String[] tableNames;
    /** 前缀 */
    private String[] tablePrefix;
    /** add.vue 代码 */
    private String addVue;
    /** edit.vue 代码 */
    private String editVue;
    /** 是否强制覆盖 */
    private boolean override;

}
