package top.bitplanet.devops.uaa.service;

import top.bitplanet.devops.uaa.entity.User;
import top.bitplanet.devops.uaa.dto.UserResp;
import top.bitplanet.devops.uaa.dto.query.UserQuery;
import top.bitplanet.devops.support.mybatisplus.base.TYBaseService;

/**
 * <p>
 * 用户登录信息 服务类
 * </p>
 *
 * @author Le
 * @since 2022-02-09
 */
public interface IUserService extends TYBaseService<User, UserQuery, UserResp> {

}
