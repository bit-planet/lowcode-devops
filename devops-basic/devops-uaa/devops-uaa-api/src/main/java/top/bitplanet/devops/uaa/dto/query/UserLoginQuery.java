package top.bitplanet.devops.uaa.dto.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserLoginQuery {

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

}
