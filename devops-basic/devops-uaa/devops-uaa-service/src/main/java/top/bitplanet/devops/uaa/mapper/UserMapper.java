package top.bitplanet.devops.uaa.mapper;

import top.bitplanet.devops.uaa.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户登录信息 Mapper 接口
 * </p>
 *
 * @author Le
 * @since 2022-02-09
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
