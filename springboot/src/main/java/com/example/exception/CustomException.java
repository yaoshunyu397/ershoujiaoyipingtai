package com.example.exception;
import com.example.common.enums.ResultCodeEnum;

/**
 * CustomException 是一个自定义异常类，继承自 RuntimeException，
 * 用于在业务逻辑中抛出带有特定状态码和提示信息的异常。
 */
public class CustomException extends RuntimeException {
    /**
     * 异常对应的状态码，用于标识异常的类型。
     */
    private String code;
    /**
     * 异常对应的提示信息，用于描述异常的具体情况。
     */
    private String msg;

    /**
     * 构造方法，使用 ResultCodeEnum 枚举来初始化异常的状态码和提示信息。
     *
     * @param resultCodeEnum 包含状态码和提示信息的枚举对象
     */
    public CustomException(ResultCodeEnum resultCodeEnum) {
        this.code = resultCodeEnum.code;
        this.msg = resultCodeEnum.msg;
    }

    /**
     * 构造方法，允许手动传入异常的状态码和提示信息。
     *
     * @param code 异常对应的状态码
     * @param msg 异常对应的提示信息
     */
    public CustomException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 获取异常对应的状态码。
     *
     * @return 异常的状态码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置异常对应的状态码。
     *
     * @param code 要设置的状态码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取异常对应的提示信息。
     *
     * @return 异常的提示信息
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 设置异常对应的提示信息。
     *
     * @param msg 要设置的提示信息
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
