package top.bitplanet.devops.lowcode.service.impl;

import com.baomidou.mybatisplus.generator.MyFastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import top.bitplanet.devops.lowcode.entity.PgTableColumn;
import top.bitplanet.devops.lowcode.entity.PgTableInfo;
import top.bitplanet.devops.lowcode.helper.UIComponentMapSqlTypeHelper;
import top.bitplanet.devops.lowcode.service.IGenCodeService;
import top.bitplanet.devops.support.core.helper.DBHelper;
import top.bitplanet.devops.lowcode.dto.query.DataSourceInfoQuery;
import top.bitplanet.devops.lowcode.dto.query.FormUIComponentInfoQuery;
import top.bitplanet.devops.support.core.helper.StringHelper;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class GenCodeServiceImpl implements IGenCodeService {
    @Override
    public void genCode4Mybatisplus(DataSourceInfoQuery query) {

        MyFastAutoGenerator.create(query.getJdbcUrl(), query.getUsername(), query.getPassword())
                .globalConfig(builder -> {
                    builder.author("Le")
                            .enableSwagger()
                            .fileOverride()
                            .outputDir("/code");
                })
                .packageConfig(builder -> {
                    builder.parent("top.bitplanet.devops")
                            .moduleName("lowcode")
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "/code"));
                })
                .strategyConfig(builder -> {
                    builder.addInclude("lcp_datasource_info", "lcp_test")
                            .addTablePrefix("lcp_")
                            .entityBuilder().enableLombok()
                            .logicDeleteColumnName("delete_flag")
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
                            .controller("/templates/mybatisplus/controller.java.vm");
                })
                .execute();
    }

    @Override
    public List<PgTableInfo> findAllTables(Connection connection, String sql) {
        List<PgTableInfo> objects = new ArrayList<>();
        try {
            objects = DBHelper.querySql(connection, sql, PgTableInfo.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objects;
    }

    @Override
    public List<PgTableColumn> findAllColumns(Connection connection, String sql, Object... params) {
        List<PgTableColumn> objects = new ArrayList<>();
        try {
            objects = DBHelper.querySql(connection, sql, PgTableColumn.class, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objects;
    }

    // TODO 完成此功能
    @Override
    public String genSqlByForm(List<FormUIComponentInfoQuery> querys, String tableName) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE IF NOT EXISTS  " + tableName + "\n");
        sb.append("(\n");
        sb.append("    id bigint NOT NULL,\n");
        // 组装字段
        for (FormUIComponentInfoQuery query : querys) {
            String componentType = query.getComponentType();
            String fieldType = UIComponentMapSqlTypeHelper.getFieldType(componentType, query.getMaxlength());
            sb.append("    " + StringHelper.toUnderlineCase(query.get__vModel__()) + " " + fieldType + ",\n");
        }
        sb.append("    create_user_id bigint,\n");
        sb.append("    update_user_id bigint,\n");
        sb.append("    create_time timestamp without time zone,\n");
        sb.append("    update_time timestamp without time zone,\n");
        sb.append("    delete_flag boolean,\n");
        sb.append("    CONSTRAINT " + tableName + "_pkey PRIMARY KEY (id)\n");
        sb.append(");\n");
        /**
         * 注释
         */
        sb.append("COMMENT ON COLUMN " + tableName + ".id IS '主键'; \n");
        sb.append("COMMENT ON COLUMN " + tableName + ".create_user_id IS '创建人ID'; \n");
        sb.append("COMMENT ON COLUMN " + tableName + ".update_user_id IS '修改人ID'; \n");
        sb.append("COMMENT ON COLUMN " + tableName + ".create_time IS '创建时间'; \n");
        sb.append("COMMENT ON COLUMN " + tableName + ".update_time IS '修改时间'; \n");
        sb.append("COMMENT ON COLUMN " + tableName + ".delete_flag IS '删除标志'; \n");
        for (FormUIComponentInfoQuery query : querys) {
            // 注释
            String comment = String.valueOf(query.get__config__().get("label"));
            sb.append("COMMENT ON COLUMN " + tableName + "." + StringHelper.toUnderlineCase(query.get__vModel__()) + " IS '" + comment + "'; \n");
        }
        return sb.toString();
    }
}
