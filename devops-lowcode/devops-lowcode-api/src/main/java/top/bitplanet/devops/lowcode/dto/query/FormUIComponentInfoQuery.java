package top.bitplanet.devops.lowcode.dto.query;

import lombok.Data;

import java.util.Map;

/**
 * 表单组件配置信息
 */
@Data
public class FormUIComponentInfoQuery {

    /** 字段名 */
    private String __vModel__;
    /** 组件类型 */
    private String componentType;
    /** 最大长度 */
    private Integer maxlength;
    /** config ['label'] */
    private Map<String,Object> __config__;

}
