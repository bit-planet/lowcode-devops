package top.bitplanet.devops.lowcode.wiki.service.impl;

import top.bitplanet.devops.lowcode.wiki.entity.WikiPage;
import top.bitplanet.devops.lowcode.wiki.dto.WikiPageResp;
import top.bitplanet.devops.lowcode.wiki.dto.query.WikiPageQuery;
import top.bitplanet.devops.lowcode.wiki.mapper.WikiPageMapper;
import top.bitplanet.devops.lowcode.wiki.service.IWikiPageService;
import org.springframework.stereotype.Service;
import top.bitplanet.devops.support.mybatisplus.base.TYBaseServiceImpl;

/**
 * <p>
 * wiki知识库page页 服务实现类
 * </p>
 *
 * @author Le
 * @since 2022-02-22
 */
@Service
public class WikiPageServiceImpl extends TYBaseServiceImpl<WikiPageMapper, WikiPage, WikiPageQuery, WikiPageResp> implements IWikiPageService {

}
