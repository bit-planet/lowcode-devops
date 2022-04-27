package top.bitplanet.devops.lowcode.wiki.mapper;

import top.bitplanet.devops.lowcode.wiki.entity.WikiPage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * wiki知识库page页 Mapper 接口
 * </p>
 *
 * @author Le
 * @since 2022-02-22
 */
@Mapper
public interface WikiPageMapper extends BaseMapper<WikiPage> {

}
