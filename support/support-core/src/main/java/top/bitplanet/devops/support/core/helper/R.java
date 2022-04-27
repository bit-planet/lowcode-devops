package top.bitplanet.devops.support.core.helper;

import top.bitplanet.devops.support.core.enums.RestStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * **************************************************************
 * @描述        : 请求响应消息模板
 * @作者        : Leo
 * @版本        : v1.0
 * @日期        : 2021/11/23
 */
@Getter
public class R<T> {
    /** 4位数字，说明：前三位为参考http状态码，后一位为扩展预留位 */
    private int code;
    /** 二级码，作为业务码或错误码用 */
    private String sub_code;
    /** 响应消息 */
    private String msg;
    /** 携带主体数据 */
    private T data;

    /**
     * 响应成功
     * @param <T>
     * @return
     */
    public static <T> R<T> buildSuccess() {
        return build(RestStatusEnum.SUCCESS);
    }

    /**
     * 响应成功，业务判读,客户端需要根据sub_code进一步处理
     * @param <T>
     * @return
     */
    public static <T> R<T> buildProceed() {
        return build(RestStatusEnum.PROCEED);
    }

    /**
     * 客户端参数异常
     * @param <T>
     * @return
     */
    public static <T> R<T> buildParamErr() {
        return build(RestStatusEnum.PARAM_ERR);
    }

    /**
     * 客户端上传文件错误
     * @param <T>
     * @return
     */
    public static <T> R<T> buildFileUploadErr() {
        return build(RestStatusEnum.FILE_UPLOAD_ERR);
    }

    /**
     * 未登录
     * @param <T>
     * @return
     */
    public static <T> R<T> buildUnauthorized() {
        return build(RestStatusEnum.UNAUTHORIZED);
    }

    /**
     * 登录/账号验证错误
     * @param <T>
     * @return
     */
    public static <T> R<T> buildAuthorizedError() {
        return build(RestStatusEnum.AUTHORIZED_ERROR);
    }

    /**
     * 权限不足
     * @param <T>
     * @return
     */
    public static <T> R<T> buildForbidden() {
        return build(RestStatusEnum.FORBIDDEN);
    }

    /**
     * 服务端错误
     * @param <T>
     * @return
     */
    public static <T> R<T> buildFail() {
        return build(RestStatusEnum.FORBIDDEN);
    }

    /**
     * 构建一个响应模板对象
     * @param statusEnum 状态枚举
     * @return
     */
    public static <T> R<T> build(RestStatusEnum statusEnum) {
        R<T> r = new R<>();
        r.setCode(statusEnum.getCode());
        r.setSub_code(statusEnum.getSub_code());
        return r;
    }

    ///////////////////////////////////////////////////////
    /////////////////////自定义参数调用///////////////////////
    ///////////////////////////////////////////////////////
    /**
     * 自定义二级码
     * @param sub_code 二级码
     * @return
     */
    public  R<T> customSubCode(String sub_code) {
        this.setSub_code(sub_code);
        return this;
    }

    /**
     * 自定义消息
     * @param msg 消息
     * @return
     */
    public R<T> customMsg(String msg) {
        this.setMsg(msg);
        return this;
    }

    /**
     * 自定义消息
     * @param data 数据
     * @return
     */
    public R<T> customData(T data) {
        this.setData(data);
        return this;
    }

    /**
     * 成功 - 简单响应
     * @param data 响应数据
     * @return
     */
    public static <T> R<T> success(T data) {
        return (R<T>) buildSuccess().customData(data);
    }

    /**
     * 成功 - 简单响应
     * @param msg  消息
     * @param data 响应数据
     * @return
     */
    public static <T> R<T> success(String msg,T data) {
        return (R<T>) buildSuccess().customMsg(msg).customData(data);
    }

    /**
     * 服务端业务处理失败（code:5000）
     * @param msg 消息
     * @return
     */
    public static <T> R<T> fail(String msg) {
        return (R<T>) buildFail().customMsg(msg);
    }

    /**
     * 服务端业务处理失败（code:5000）
     * @param msg 消息
     * @param data 数据
     * @return
     */
    public static <T> R<T> fail(String msg,T data) {
        return (R<T>) buildFail().customMsg(msg).customData(data);
    }


    private R() {
        // 私有化
    }
    ////////// setter /////////////
    private void setCode(int code) {
        this.code = code;
    }

    private void setSub_code(String sub_code) {
        this.sub_code = sub_code;
    }

    private void setMsg(String msg) {
        this.msg = msg;
    }

    private void setData(T data) {
        this.data = data;
    }
}
