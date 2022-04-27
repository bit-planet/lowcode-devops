package top.bitplanet.devops.uaa.service.impl;

import top.bitplanet.devops.uaa.entity.User;
import top.bitplanet.devops.uaa.dto.UserResp;
import top.bitplanet.devops.uaa.dto.query.UserQuery;
import top.bitplanet.devops.uaa.mapper.UserMapper;
import top.bitplanet.devops.uaa.service.IUserService;
import org.springframework.stereotype.Service;
import top.bitplanet.devops.support.mybatisplus.base.TYBaseServiceImpl;

/**
 * <p>
 * 用户登录信息 服务实现类
 * </p>
 *
 * @author Le
 * @since 2022-02-09
 */
@Service
public class UserServiceImpl extends TYBaseServiceImpl<UserMapper, User, UserQuery, UserResp> implements IUserService {

}
