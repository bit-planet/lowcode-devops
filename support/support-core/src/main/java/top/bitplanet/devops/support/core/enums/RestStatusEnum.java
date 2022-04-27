package top.bitplanet.devops.support.core.enums;

/**
 * **************************************************************
 *
 * @描述        : ajax 返回信息状态码
 * @作者        : 乐胜
 * @版本        : v1.0
 * @日期        : 2016/3/30
 * @修改日志    :
 * @修改人    :
 * @修订后版本: v2.0
 * @修改时间    : ***************************************************************
 */
public enum RestStatusEnum {

    /** 成功 */
    SUCCESS(2000,"SUCCESS"),
    /** 响应成功，业务判读,客户端需要根据sub_code进一步处理 */
    PROCEED(2001,"PROCEED"),

    ///////////////////////////////////////////////////////
    ////////////////////客户端错误///////////////////////////
    ///////////////////////////////////////////////////////
    /** 客户端参数异常  */
    PARAM_ERR(4000,"PARAM_ERR"),
    /** 上传文件异常 */
    FILE_UPLOAD_ERR(4001,"FILE_UPLOAD_ERR"),
    /** 未登录、未授权 */
    UNAUTHORIZED(4010,"UNAUTHORIZED"),
    /** 登录/账号验证异常 */
    AUTHORIZED_ERROR(4011,"AUTHORIZED_ERROR"),
    /** 拒绝访问，权限不足 */
    FORBIDDEN(4030,"FORBIDDEN"),

    ///////////////////////////////////////////////////////
    ////////////////////服务端错误///////////////////////////
    ///////////////////////////////////////////////////////
    /** 服务异常 */
    FAIL(5000,"FAIL");

    /**
     * code 4位数字，说明：前三位为参考http状态码，后一位为扩展预留位
     */
    private final int code;
    /**
     * 二级码：业务码/错误码
     */
    private final String sub_code;

    RestStatusEnum(int code,String sub_code) {
        this.code = code;
        this.sub_code = sub_code;
    }

    public int getCode() {
        return code;
    }

    public String getSub_code() {
        return sub_code;
    }
}
