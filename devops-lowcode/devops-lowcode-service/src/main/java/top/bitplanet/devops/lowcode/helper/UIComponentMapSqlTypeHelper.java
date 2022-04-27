package top.bitplanet.devops.lowcode.helper;

import java.util.HashMap;
import java.util.Map;

public class UIComponentMapSqlTypeHelper {

    // key : 组件类型  value ：对应数据库类型
    private static Map<String,String> pgsqlMap;


    static {
        pgsqlMap = new HashMap<>();
        pgsqlMap.put("input_text","character varying");
        pgsqlMap.put("input_password","character varying");
        pgsqlMap.put("textarea","character varying");
        pgsqlMap.put("input_number","integer");
        pgsqlMap.put("radio","integer");
        pgsqlMap.put("switch","boolean");
        pgsqlMap.put("slide","integer");
        pgsqlMap.put("editor","text");
    }

    /**
     * 根据UI组件类型获取对应的数据库字段类型
     * @param componentType  ui组件类型
     * @param maxLength      最大长度
     * @return
     */
    public static String getFieldType(String componentType,Integer maxLength) {
        String fieldType = pgsqlMap.get(componentType);
        fieldType = fieldType == null ? "character varying":fieldType;
        if (maxLength != null && maxLength > 0) {
            fieldType = fieldType + "(" + maxLength +")";
        }
        return fieldType;
    }
}
