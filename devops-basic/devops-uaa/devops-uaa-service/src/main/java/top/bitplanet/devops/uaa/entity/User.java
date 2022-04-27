package top.bitplanet.devops.uaa.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import top.bitplanet.devops.support.mybatisplus.base.BaseEntity;
import java.io.Serializable;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import top.bitplanet.devops.uaa.dto.UserResp;
import top.bitplanet.devops.uaa.dto.query.UserQuery;

/**
 * <p>
 * 用户登录信息
 * </p>
 *
 * @author Le
 * @since 2022-02-09
 */
@Data
@TableName("uaa_user")
@Api(value = "User对象", tags = "用户登录信息")
public class User extends BaseEntity<UserQuery,UserResp> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("头像")
    private String portrait;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;


}
