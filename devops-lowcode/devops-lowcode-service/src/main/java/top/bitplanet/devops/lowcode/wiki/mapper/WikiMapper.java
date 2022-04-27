package top.bitplanet.devops.lowcode.wiki.mapper;

import top.bitplanet.devops.lowcode.wiki.entity.Wiki;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * wiki知识库 Mapper 接口
 * </p>
 *
 * @author Le
 * @since 2022-02-21
 */
@Mapper
public interface WikiMapper extends BaseMapper<Wiki> {

}
