package top.bitplanet.devops.lowcode.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import top.bitplanet.devops.lowcode.dto.query.DataSourceInfoQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 数据源信息11
 * </p>
 *
 * @author Le
 * @since 2021-11-18
 */
@Data
@TableName("lcp_datasource_info")
@Api(value = "DatasourceInfo对象", tags = "数据源信息")
public class DatasourceInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("连接名称")
    private String name;

    @ApiModelProperty("主机")
    private String host;

    @ApiModelProperty("端口")
    private String port;

    @ApiModelProperty("数据库类型 1:pgsql")
    private Integer type;

    @ApiModelProperty("默认数据库")
    private String database;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("创建用户")
    private Long createUserId;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("修改用户")
    private Long updateUserId;

    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;


    @TableLogic
    @ApiModelProperty("逻辑删除字段")
    private Boolean deleteFlag;


    public  DataSourceInfoQuery convertToQuery() {
        DataSourceInfoQuery query = new DataSourceInfoQuery();
        StringBuilder sb = new StringBuilder("");
        sb.append("jdbc:postgresql://");
        sb.append(this.getHost());
        sb.append(":");
        sb.append(this.getPort());
        // 默认数据库
        sb.append("/"+this.getDatabase());
        query.setJdbcUrl(sb.toString());
        query.setUsername(this.getUsername());
        query.setPassword(this.getPassword());
        return query;
    }

}
