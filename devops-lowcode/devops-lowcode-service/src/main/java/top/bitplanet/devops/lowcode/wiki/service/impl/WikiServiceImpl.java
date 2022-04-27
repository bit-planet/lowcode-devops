package top.bitplanet.devops.lowcode.wiki.service.impl;

import top.bitplanet.devops.lowcode.wiki.entity.Wiki;
import top.bitplanet.devops.lowcode.wiki.dto.WikiResp;
import top.bitplanet.devops.lowcode.wiki.dto.query.WikiQuery;
import top.bitplanet.devops.lowcode.wiki.mapper.WikiMapper;
import top.bitplanet.devops.lowcode.wiki.service.IWikiService;
import org.springframework.stereotype.Service;
import top.bitplanet.devops.support.mybatisplus.base.TYBaseServiceImpl;

/**
 * <p>
 * wiki知识库 服务实现类
 * </p>
 *
 * @author Le
 * @since 2022-02-21
 */
@Service
public class WikiServiceImpl extends TYBaseServiceImpl<WikiMapper, Wiki, WikiQuery, WikiResp> implements IWikiService {

}
