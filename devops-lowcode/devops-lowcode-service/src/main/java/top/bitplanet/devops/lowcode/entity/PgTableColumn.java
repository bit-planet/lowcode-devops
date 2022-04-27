package top.bitplanet.devops.lowcode.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Api(value = "PgTableColumn对象", tags = "数据列信息")
public class PgTableColumn {

    @ApiModelProperty("列名")
    private String columnName;

    @ApiModelProperty("数据类型")
    private String type;

    @ApiModelProperty("注释")
    private String comment;

    @ApiModelProperty("非空")
    private Boolean notnull;
}
