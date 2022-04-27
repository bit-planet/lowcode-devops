package top.bitplanet.devops.lowcode.wiki.service;

import top.bitplanet.devops.lowcode.wiki.entity.WikiPage;
import top.bitplanet.devops.lowcode.wiki.dto.WikiPageResp;
import top.bitplanet.devops.lowcode.wiki.dto.query.WikiPageQuery;
import top.bitplanet.devops.support.mybatisplus.base.TYBaseService;

/**
 * <p>
 * wiki知识库page页 服务类
 * </p>
 *
 * @author Le
 * @since 2022-02-22
 */
public interface IWikiPageService extends TYBaseService<WikiPage, WikiPageQuery, WikiPageResp> {

}
