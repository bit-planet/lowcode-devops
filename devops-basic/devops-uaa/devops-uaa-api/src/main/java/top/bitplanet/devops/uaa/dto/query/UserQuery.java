package top.bitplanet.devops.uaa.dto.query;

import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 用户登录信息 通用Query对象
 * </p>
 *
 * @author Le
 * @since 2022-02-09
 */
@Data
@Api(value = "UserQuery 对象", tags = "用户登录信息")
public class UserQuery implements Serializable {

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

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("创建人ID")
    private Long createUserId;

    @ApiModelProperty("修改人ID")
    private Long updateUserId;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("删除标志")
    private Boolean deleteFlag;


}
