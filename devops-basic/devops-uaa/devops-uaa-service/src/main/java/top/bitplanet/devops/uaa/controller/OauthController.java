package top.bitplanet.devops.uaa.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import top.bitplanet.devops.support.core.helper.JwtHelper;
import top.bitplanet.devops.support.core.helper.R;
import top.bitplanet.devops.uaa.dto.UserResp;
import top.bitplanet.devops.uaa.dto.query.UserLoginQuery;
import top.bitplanet.devops.uaa.dto.query.UserQuery;
import top.bitplanet.devops.uaa.entity.User;
import top.bitplanet.devops.uaa.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/oauth")
public class OauthController {

    @Resource
    private IUserService userService;

    @PostMapping("/token")
    public R token(@RequestBody UserLoginQuery userLoginQuery) {
        User user = userService.getOne(
                new QueryWrapper<User>()
                        .lambda()
                        .eq(User::getUsername, userLoginQuery.getUsername())
        );
        UserResp userResp = user.covertToResp();
        if (userResp == null) {
            return R.fail("验证失败！检查用户名或密码");
        }
        // 比对密码
        String inputPassword = userLoginQuery.getPassword();
        BCryptPasswordEncoder bpe = new BCryptPasswordEncoder();
        if (!bpe.matches(inputPassword,userResp.getPassword())) {
            return R.fail("验证失败！检查用户名或密码");
        }
        // 验证通过。换取token
        String token = JwtHelper.createWithRsa256(5 * 60);
        return R.success(token);
    }
}
