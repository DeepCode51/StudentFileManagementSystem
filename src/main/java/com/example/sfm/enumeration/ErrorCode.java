package com.example.sfm.enumeration;

/**
 * http错误码枚举类
 */
public enum ErrorCode {
    /** 普通 */
    GENERAL("100", "普通"),

    /** 警告 */
    WARING("101", "警告"),

    /** 成功 */
    SUCCESS("200", "成功"),

    /** 操作失败 */
    FAIL("205", "操作失败"),

    /** 错误 */
    ERROR("400", "错误"),

    /** 未授权 */
    UNAUTHORIZED("401", "未授权"),

    /** 不允许访问 */
    FORBIDDEN("403", "不允许访问"),

    /** AuthCode错误 */
    INVALID_AUTHCODE("444", "无效的AuthCode"),

    /** 太频繁的调用 */
    TOO_FREQUENT("445", "太频繁的调用"),

    /** 未知的错误 */
    UNKNOWN_ERROR("499", "未知错误"),

    /** 系统错误 */
    SYSTEM_ERROR("500", "系统错误"),

    /** 用户名或密码错误 */
    BAD_REQUEST("530", "用户名或密码错误");

    private String code;
    private String message;
    private ErrorCode(String code, String message){
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
