package top.bitplanet.devops.uaa.service.impl;


import top.bitplanet.devops.support.core.framework.page.TYPageQuery;
import top.bitplanet.devops.support.core.framework.page.TYPageResp;
import top.bitplanet.devops.uaa.entity.User;
import top.bitplanet.devops.uaa.mapper.UserMapper;
import top.bitplanet.devops.uaa.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

import java.util.List;

@SpringBootTest
@Slf4j
class UserServiceImplTest {

    @Resource
    private IUserService userService;

    @Resource
    private UserMapper userMapper;

    @Test
    public void add() {
        User user = new User();
        user.setUsername("zhangsan");
        String encode = new BCryptPasswordEncoder().encode("123456");
        user.setPassword(encode);
        int insert = userMapper.insert(user);
        log.info("影响行数:"+insert);
    }

    @Test
    public void queryUserForPage(){

        //TYBaseServiceImpl userUserMapperTYBaseService = new TYBaseServiceImpl<UserMapper, User>();
        //System.out.println(userUserMapperTYBaseService);
        TYPageResp<User> userPage = userService.page(new TYPageQuery<User>(1,2,null),null);
        List<User> list = userPage.getRecords();
        for(User user : list){
            System.out.println(user);
        }
    }

}