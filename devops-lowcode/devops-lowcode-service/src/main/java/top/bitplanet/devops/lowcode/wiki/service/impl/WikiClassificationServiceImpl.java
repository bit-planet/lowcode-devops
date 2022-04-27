package top.bitplanet.devops.lowcode.wiki.service.impl;

import top.bitplanet.devops.lowcode.wiki.entity.WikiClassification;
import top.bitplanet.devops.lowcode.wiki.dto.WikiClassificationResp;
import top.bitplanet.devops.lowcode.wiki.dto.query.WikiClassificationQuery;
import top.bitplanet.devops.lowcode.wiki.mapper.WikiClassificationMapper;
import top.bitplanet.devops.lowcode.wiki.service.IWikiClassificationService;
import org.springframework.stereotype.Service;
import top.bitplanet.devops.support.mybatisplus.base.TYBaseServiceImpl;

/**
 * <p>
 * wiki分类 服务实现类
 * </p>
 *
 * @author Le
 * @since 2022-03-07
 */
@Service
public class WikiClassificationServiceImpl extends TYBaseServiceImpl<WikiClassificationMapper, WikiClassification, WikiClassificationQuery, WikiClassificationResp> implements IWikiClassificationService {

}
