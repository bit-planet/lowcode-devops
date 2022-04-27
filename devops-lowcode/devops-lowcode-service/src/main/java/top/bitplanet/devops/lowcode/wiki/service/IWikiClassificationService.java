package top.bitplanet.devops.lowcode.wiki.service;

import top.bitplanet.devops.lowcode.wiki.entity.WikiClassification;
import top.bitplanet.devops.lowcode.wiki.dto.WikiClassificationResp;
import top.bitplanet.devops.lowcode.wiki.dto.query.WikiClassificationQuery;
import top.bitplanet.devops.support.mybatisplus.base.TYBaseService;

/**
 * <p>
 * wiki分类 服务类
 * </p>
 *
 * @author Le
 * @since 2022-03-07
 */
public interface IWikiClassificationService extends TYBaseService<WikiClassification, WikiClassificationQuery, WikiClassificationResp> {

}
