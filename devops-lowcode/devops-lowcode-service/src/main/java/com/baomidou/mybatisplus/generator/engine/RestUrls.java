package com.baomidou.mybatisplus.generator.engine;

import lombok.Data;

/**
 * <p>
 *  rest风格 url 地址
 * </p>
 *
 * @author Le
 * @version 1.0.0
 * @since 2021/12/24 11:35
 */
@Data
public class RestUrls {

    /** context-path */
    private String contextPath;
    /** rest请求 根url */
    private String baseUrl;
    /** 新增单个对象 */
    private String addUrl = "";
    /** 根据id删除单个对象 */
    private String deleteByIdUrl = "/{id}";
    /** 根据id修改对象 */
    private String updateByIdUrl = "/{id}";
    /** 详情 */
    private String detailUrl="/{id}";
    /** 分页 */
    private String pageUrl="/page";

}
