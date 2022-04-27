package top.bitplanet.devops.lowcode.dto.query;

import top.bitplanet.devops.support.core.helper.InitialHelper;
import lombok.Data;

/**
 *  项目元数据
 */
@Data
public class ProjectMetaDataQuery {

    /** 公司/组织名 */
    private String companyName;
    /** 产品/项目名 */
    private String productName;
    /** 模块名 */
    private String  moduleName;
    /** 子模块名 */
    private String subModuleName;
    /** 版本 */
    private String version;
    /** 项目描述（pom） */
    private String description;

    /**
     * 分级模块名转换成 - 分割
     * @return
     */
    public String getModuleHyphen() {
        return moduleName.replaceAll("\\.","-" );
    }
    /**
     * 分级模块名转换成 / 分割,生成文件夹用
     * @return
     */
    public String getModuleFolder() {
        return moduleName.replaceAll("\\.","/" );
    }

    /**
     * 根据模块名 生成application类名
     * @return
     */
    public String getModuleApplicationClassName() {
        String[] split = moduleName.split("\\.");
        String className = "";
        for (String s : split) {
            className += InitialHelper.toUpperCase(s);
        }
        className += "ServiceApplication";
        return className;
    }
}
