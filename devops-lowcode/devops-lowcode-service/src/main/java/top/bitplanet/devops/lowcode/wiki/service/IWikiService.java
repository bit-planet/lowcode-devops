package top.bitplanet.devops.lowcode.wiki.service;

import top.bitplanet.devops.lowcode.wiki.entity.Wiki;
import top.bitplanet.devops.lowcode.wiki.dto.WikiResp;
import top.bitplanet.devops.lowcode.wiki.dto.query.WikiQuery;
import top.bitplanet.devops.support.mybatisplus.base.TYBaseService;

/**
 * <p>
 * wiki知识库 服务类
 * </p>
 *
 * @author Le
 * @since 2022-02-21
 */
public interface IWikiService extends TYBaseService<Wiki, WikiQuery, WikiResp> {

}
