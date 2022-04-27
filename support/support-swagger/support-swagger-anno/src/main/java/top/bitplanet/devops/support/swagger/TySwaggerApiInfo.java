package top.bitplanet.devops.support.swagger;




public class TySwaggerApiInfo {

    private boolean enable;
    /** 对应 html title */
    private String title;
    /** 文档描述 */
    private String description;
    /** 联系人名称 */
    private String contactName;
    /** 联系url地址 */
    private String contactUrl;
    /** 联系邮箱 */
    private String contactEmail;
    /** 项目版本 */
    //@Value("${maven.project.version}")
    private String version;

    public boolean getEnable() {
        return enable;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getContactName() {
        return contactName;
    }

    public String getContactUrl() {
        return contactUrl;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public String getVersion() {
        return version;
    }
}
