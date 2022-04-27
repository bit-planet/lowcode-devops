package top.bitplanet.devops.support.swagger;

import java.lang.annotation.*;
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface TyEnableOpenApi {

    /** 是否弃用 */
    boolean enable() default false;
    /** 对应 html title */
    String title() default "比特星球api文档";
    /** 文档描述 */
    String description() default "";
    /** 联系人名称 */
    String contactName() default  "比特星球";
    /** 联系url地址 */
    String contactUrl() default "www.bitplanet.top";
    /** 联系邮箱 */
    String contactEmail() default "leo2020trust@gmail.com";
    /** 项目版本 */
    String version() default  "";

}
